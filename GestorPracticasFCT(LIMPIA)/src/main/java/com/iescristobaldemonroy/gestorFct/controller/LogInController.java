
package com.iescristobaldemonroy.gestorFct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iescristobaldemonroy.gestorFct.entity.PersonaContacto;
import com.iescristobaldemonroy.gestorFct.service.PersonaContactoService;

@Controller
@RequestMapping(value = "/login")
public class LogInController {
	@Autowired
	private PersonaContactoService personaContactoService;

	@RequestMapping(method = RequestMethod.GET)
	public String displaySortedMembers(Model model) {
		model.addAttribute("newMember", new PersonaContacto());
		return "logIn";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerNewMember(@ModelAttribute("newMember") PersonaContacto newMember, BindingResult result,
			Model model) {

		if (!result.hasErrors()) {
			try {
				System.err.println(newMember.getNombre());
				return "redirect:/login";
			} catch (UnexpectedRollbackException e) {
				model.addAttribute("error", e.getCause().getCause());
				return "login";
			}
		} else {
			model.addAttribute("members", personaContactoService.getAllPersonaContacto());
			return "index";
		}
	}
}
