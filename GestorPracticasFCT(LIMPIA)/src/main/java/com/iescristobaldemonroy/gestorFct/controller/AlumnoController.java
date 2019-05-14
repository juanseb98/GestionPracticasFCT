
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
@RequestMapping(value = "/alumno")
public class AlumnoController {
	@Autowired
	private PersonaService personaService;

	@RequestMapping(method = RequestMethod.GET)
	public String displaySortedMembers(@RequestParam(value = "dni", required = true) String dni, Model model) {
		System.out.println(dni);
		model.addAttribute("alumno", new Alumno("20503879T", "juan sebastian"));
		return "alumno";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerNewMember(@ModelAttribute("alumno") Alumno alumno, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			try {

			} catch (NullPointerException e) {
				model.addAttribute("error", "No se ha encontrado usuario");
				return "alumno";
			}
		} else {
			return "alumno";
		}
		return "alumno";
	}

}
