
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

import com.iescristobaldemonroy.gestorFct.entity.PersonaContacto;
import com.iescristobaldemonroy.gestorFct.form.InteresadoForm;
import com.iescristobaldemonroy.gestorFct.form.LogInForm;
import com.iescristobaldemonroy.gestorFct.service.PersonaContactoService;

@Controller
@RequestMapping(value = "/login")
public class LogInController {
	@Autowired
	private PersonaContactoService personaContactoService;

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
		}
		if (StringUtils.isEmpty(intentoLogueo.getPassword())) {
			result.rejectValue("password", "error.campoObligatorio");
		}

		if (!result.hasErrors()) {
			try {
				// TODO Controlar inicio comprobar que existe comprobar contraseña y comprobar
				// que tipo de usuario es

				return "redirect:/login";
			} catch (UnexpectedRollbackException e) {
				model.addAttribute("error", e.getCause().getCause());
				return "redirect:/login";
			}
		} else {
			System.out.println("Errores");
			return "redirect:/login";
		}
	}
}
