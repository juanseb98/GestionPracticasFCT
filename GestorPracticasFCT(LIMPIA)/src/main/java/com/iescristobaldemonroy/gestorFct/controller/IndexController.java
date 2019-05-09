
package com.iescristobaldemonroy.gestorFct.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.iescristobaldemonroy.gestorFct.entity.Notificacion;
import com.iescristobaldemonroy.gestorFct.entity.PersonaContacto;
import com.iescristobaldemonroy.gestorFct.entity.TipoNotificacion;
import com.iescristobaldemonroy.gestorFct.form.PersonaContactoForm;
import com.iescristobaldemonroy.gestorFct.service.EmpresaService;
import com.iescristobaldemonroy.gestorFct.service.NotificacionService;
import com.iescristobaldemonroy.gestorFct.service.PersonaContactoService;

@Controller
@RequestMapping(value = "/")
public class IndexController {

	@Autowired
	private PersonaContactoService personaContactoService;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private NotificacionService notificacionService;

	private PersonaContacto log;

	@RequestMapping(method = RequestMethod.GET)
	public String displaySortedMembers(Model model) {
		model.addAttribute("newInteresado", new PersonaContactoForm());
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerNewMember(@ModelAttribute("newMember") PersonaContactoForm newInteresado,
			BindingResult result, Model model) {
		if (StringUtils.isEmpty(newInteresado.getNombre())) {
			result.rejectValue("nombre", "error.campoObligatorio");
		}
		if (StringUtils.isEmpty(newInteresado.getTelefono())) {
			result.rejectValue("telefono", "error.campoObligatorio");
		}
		if (StringUtils.isEmpty(newInteresado.getEmail())) {
			result.rejectValue("email", "error.campoObligatorio");
		}
		if (StringUtils.isEmpty(newInteresado.getEmpresa())) {
			result.rejectValue("cifEmpresa", "error.campoObligatorio");
		}

		if (!result.hasErrors()) {
			try {
				if (!ExisteEmpresa(newInteresado.getEmpresa())) {
					Empresa empresaProv = new Empresa(Constantes.DEFAULT_CIF, newInteresado.getEmpresa());
					empresaService.save(empresaProv);
					PersonaContacto interesado = new PersonaContacto(newInteresado.getEmail(),
							newInteresado.getNombre(), newInteresado.getTelefono(), empresaProv);

					personaContactoService.save(interesado);

					// TODO
					// Notificacion notificacion = new Notificacion("Nueva empresa interesada en
					// realizar contrato", "s",
					// TipoNotificacion.valueOf("NuevoInteresado").toString());
					//
					// notificacionService.save(notificacion);
					// System.out.println("añadido correctamente");
				} else {
					// TODO realizar notificacion en pantalla empresa ya existente
					System.out.println("La empresa ya esta asociada con nuestra institución");
				}

				return "redirect:/";
			} catch (UnexpectedRollbackException e) {
				model.addAttribute("error", e.getCause().getCause());
				return "index";
			}
		} else {
			return "index";
		}
	}

	private boolean ExisteEmpresa(String denominacion) {

		return empresaService.exist(denominacion);

	}

}
