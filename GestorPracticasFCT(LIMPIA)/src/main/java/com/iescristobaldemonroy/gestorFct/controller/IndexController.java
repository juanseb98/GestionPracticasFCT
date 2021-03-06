
package com.iescristobaldemonroy.gestorFct.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iescristobaldemonroy.gestorFct.entity.Interesado;
import com.iescristobaldemonroy.gestorFct.entity.Notificacion;
import com.iescristobaldemonroy.gestorFct.form.InteresadoForm;
import com.iescristobaldemonroy.gestorFct.service.EmpresaService;
import com.iescristobaldemonroy.gestorFct.service.InteresadoService;
import com.iescristobaldemonroy.gestorFct.service.NotificacionService;
import com.iescristobaldemonroy.gestorFct.util.Constantes;

@Controller
@RequestMapping(value = "/")
public class IndexController {

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private InteresadoService interesadoService;

	@Autowired
	private NotificacionService notificacionService;

	/**
	 * Metodo para iniciar la pantalla inicial y principal de la aplicacion
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initPage(Model model, HttpSession session) {
		model.addAttribute("newInteresado", new InteresadoForm());
		return "index";
	}

	/**
	 * Metodo que se ejecuta cuando se intenta registrar un nuevo interesado
	 * 
	 * @param newInteresado
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String registerNewInteresado(@ModelAttribute("newInteresado") InteresadoForm newInteresado,
			BindingResult result, Model model) {
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

	/**
	 * Metodo para comprobar los datos introducidos por el usurio
	 * 
	 * @param newInteresado
	 * @param result
	 */
	private void comprobarDatos(InteresadoForm newInteresado, BindingResult result) {
		if (StringUtils.isEmpty(newInteresado.getNombre())) {
			result.rejectValue("nombre", "error.campoObligatorio");
		} else if (newInteresado.getNombre().length() > 50) {
			result.rejectValue("nombre", "error.datoMuyLargo");
		}

		if (StringUtils.isEmpty(newInteresado.getTelefono())) {
			result.rejectValue("telefono", "error.campoObligatorio");
		} else if (newInteresado.getTelefono().length() > 9) {
			result.rejectValue("telefono", "error.datoMuyLargo");
		} else {
			try {
				Integer.parseInt(newInteresado.getTelefono());
			} catch (NumberFormatException ex) {
				result.rejectValue("telefono", "error.numerico");
			}
		}
		if (StringUtils.isEmpty(newInteresado.getEmail())) {
			result.rejectValue("email", "error.campoObligatorio");
		} else if (newInteresado.getEmail().length() > 100) {
			result.rejectValue("email", "error.datoMuyLargo");
		} else if (!newInteresado.getEmail().contains("@")) {
			result.rejectValue("email", "error.datoIncorrecto");
		}
		if (StringUtils.isEmpty(newInteresado.getEmpresa())) {
			result.rejectValue("empresa", "error.campoObligatorio");
		}
	}

	/**
	 * Comprueba si existe alguna empresa con el nombre introducido
	 * 
	 * @param denominacion
	 * @return
	 */
	private boolean ExisteEmpresa(String denominacion) {
		return empresaService.exist(denominacion);

	}

}
