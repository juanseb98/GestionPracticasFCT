
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
import org.springframework.web.bind.annotation.RequestParam;

import com.iescristobaldemonroy.gestorFct.entity.Administrador;
import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.entity.Interesado;
import com.iescristobaldemonroy.gestorFct.entity.Persona;
import com.iescristobaldemonroy.gestorFct.entity.TutorDocente;
import com.iescristobaldemonroy.gestorFct.form.InteresadoForm;
import com.iescristobaldemonroy.gestorFct.form.LogInForm;
import com.iescristobaldemonroy.gestorFct.service.PersonaService;

@Controller
@RequestMapping(value = "/login")
public class LogInController {
	@Autowired
	private PersonaService personaService;

	@RequestMapping(method = RequestMethod.GET)
	public String displaySortedMembers(Model model) {
		model.addAttribute("newMember", new LogInForm());
		return "logIn";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerNewMember(@ModelAttribute("newMember") LogInForm intentoLogueo, BindingResult result,
			Model model) {

		if (StringUtils.isEmpty(intentoLogueo.getDni())) {
			result.rejectValue("dni", "error.campoObligatorio");
			System.out.println("Error dni");
		}

		if (StringUtils.isEmpty(intentoLogueo.getPassword())) {
			result.rejectValue("password", "error.campoObligatorio");
			System.out.println("Error password");
		}

		if (!result.hasErrors()) {
			try {
				Persona per = personaService.getPersonaByDni(intentoLogueo.getDni());

				if (per.getAdministrador() != null) {
					Administrador administrador = per.getAdministrador();
					if (administrador.getContrasenia().equals(intentoLogueo.getPassword())) {
						return "administracion";
					} else {
						result.rejectValue("password", "error.datoIncorrecto");
					}
				} else if (per.getAlumno() != null) {
					Alumno alumno = per.getAlumno();
					if (alumno.getContrasenia().equals(intentoLogueo.getPassword())) {
						return "redirect: alumno?dni=" + alumno.getDni();
					} else {
						result.rejectValue("password", "error.datoIncorrecto");
					}
				} else if (per.getTutorDocente() != null) {
					TutorDocente tutorDocente = per.getTutorDocente();
					if (tutorDocente.getContrasenia().equals(intentoLogueo.getPassword())) {
						return "tutorDocente";
					} else {
						result.rejectValue("password", "error.datoIncorrecto");
					}
				}
				model.addAttribute("newInteresado", new InteresadoForm());
				return "index";
			} catch (NullPointerException e) {
				model.addAttribute("error", "No se ha encontrado usuario");
				return "logIn";
			}
		} else {
			return "logIn";
		}
	}

}
