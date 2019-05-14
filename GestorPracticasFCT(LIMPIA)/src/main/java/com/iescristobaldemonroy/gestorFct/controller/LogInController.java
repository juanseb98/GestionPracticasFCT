
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

import com.iescristobaldemonroy.gestorFct.entity.Persona;
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

				return "logIn";
			} catch (UnexpectedRollbackException e) {
				model.addAttribute("error", e.getCause().getCause());
				return "logIn";
			}
		} else {
			return "logIn";
		}
	}
}
