
package com.iescristobaldemonroy.gestorFct.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iescristobaldemonroy.gestorFct.constantes.Constantes;
import com.iescristobaldemonroy.gestorFct.entity.Empresa;
import com.iescristobaldemonroy.gestorFct.entity.Interesado;
import com.iescristobaldemonroy.gestorFct.entity.Notificacion;
import com.iescristobaldemonroy.gestorFct.entity.PersonaContacto;
import com.iescristobaldemonroy.gestorFct.form.InteresadoForm;
import com.iescristobaldemonroy.gestorFct.service.EmpresaService;
import com.iescristobaldemonroy.gestorFct.service.InteresadoService;
import com.iescristobaldemonroy.gestorFct.service.NotificacionService;
import com.iescristobaldemonroy.gestorFct.service.NotificacionServiceImpl;
import com.iescristobaldemonroy.gestorFct.service.PersonaContactoService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Controller
@RequestMapping(value = "/")
public class IndexController {

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private InteresadoService interesadoService;

	@Autowired
	private NotificacionService notificacionService;

	@RequestMapping(method = RequestMethod.GET)
	public String displaySortedMembers(Model model) {
		model.addAttribute("newInteresado", new InteresadoForm());
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerNewMember(@ModelAttribute("newInteresado") InteresadoForm newInteresado, BindingResult result,
			Model model) {
		comprobarDatos(newInteresado, result);

		if (!result.hasErrors()) {
			try {
				if (!ExisteEmpresa(newInteresado.getEmpresa())) {
					Interesado interesado = new Interesado(newInteresado.getEmail(), newInteresado.getNombre(),
							newInteresado.getEmpresa(), newInteresado.getTelefono());

					interesadoService.save(interesado);

					Notificacion notificacion = new Notificacion(Constantes.MENSAJE_NUEVO_INTERESADO,
							Constantes.BOOLEAN_TRUE, Constantes.NOTIFICACION_NUEVO_INTERESADO,
							interesadoService.getInteresadoByTelefono(interesado.getTelefono()), null);

					notificacionService.save(notificacion);

					System.out.println("añadido correctamente");
				} else {
					// TODO realizar notificacion en pantalla empresa ya existente
					System.out.println("La empresa ya esta asociada con nuestra institución");
				}

				return "redirect:/";
			} catch (UnexpectedRollbackException e) {
				model.addAttribute("error", e.getCause().getCause());
				return "index";
			} catch (ConstraintViolationException ex) {
				model.addAttribute("error", ex.getCause().getCause());
				return "index";
			} catch (DataIntegrityViolationException exxx) {
				result.rejectValue("telefono", "error.campoDuplicado");
				return "index";
			} catch (Exception exx) {
				model.addAttribute("error", exx.getCause().getCause());
				return "index";
			}
		} else {
			return "index";
		}
	}

	private void comprobarDatos(InteresadoForm newInteresado, BindingResult result) {
		if (StringUtils.isEmpty(newInteresado.getNombre())) {
			result.rejectValue("nombre", "error.campoObligatorio");
		}

		if (StringUtils.isEmpty(newInteresado.getTelefono())) {
			result.rejectValue("telefono", "error.campoObligatorio");
		} else {
			try {
				Integer.parseInt(newInteresado.getTelefono());
			} catch (NumberFormatException ex) {
				result.rejectValue("telefono", "error.campoObligatorio");
			}
		}

		if (StringUtils.isEmpty(newInteresado.getEmail())) {
			result.rejectValue("email", "error.numerico");
		}
		if (StringUtils.isEmpty(newInteresado.getEmpresa())) {
			result.rejectValue("empresa", "error.campoObligatorio");
		}
	}

	private boolean ExisteEmpresa(String denominacion) {
		return empresaService.exist(denominacion);

	}

}
