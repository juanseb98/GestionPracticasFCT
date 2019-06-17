
package com.iescristobaldemonroy.gestorFct.controller;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iescristobaldemonroy.gestorFct.entity.Administrador;
import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.entity.Persona;
import com.iescristobaldemonroy.gestorFct.entity.TutorDocente;
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
	public String inicio(@ModelAttribute("newMember") LogInForm intentoLogueo, BindingResult result, Model model,
			HttpSession session) {

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
				// Comprobamos si es administrador
				if (per.getAdministrador() != null) {
					Administrador administrador = per.getAdministrador();
					if (administrador.getContrasenia().equals(intentoLogueo.getPassword())) {
						session.setAttribute("personaLog", administrador);
						String DniEncriptado = encriptar(administrador.getDni());
						return "redirect: administracion";
					} else {
						// Comprobamos contraseña
						result.rejectValue("password", "error.datoIncorrecto");
					}
					// Comprobamos si es alumno
				} else if (per.getAlumno() != null) {
					Alumno alumno = per.getAlumno();
					// Comprobamos contraseña
					if (alumno.getContrasenia().equals(intentoLogueo.getPassword())) {
						session.setAttribute("personaLog", alumno);
						return "redirect: alumno";
					} else {
						// Comprobamos contraseña
						result.rejectValue("password", "error.datoIncorrecto");
					}
					// Comprobamos si es tutor docente
				}
				// else if (per.getTutorDocente() != null) {
				// TutorDocente tutorDocente = per.getTutorDocente();
				// if (tutorDocente.getContrasenia().equals(intentoLogueo.getPassword())) {
				// return "tutorDocente";
				// } else {
				// result.rejectValue("password", "error.datoIncorrecto");
				// }
				// }
			} catch (NullPointerException e) {
				model.addAttribute("error", "No se ha encontrado usuario");
				return "logIn";
			}
		}
		return "logIn";
	}

	private String encriptar(String dni) {
		String secretKey = "qualityinfosolutions"; // llave para encriptar datos
		String base64EncryptedString = "";

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] plainTextBytes = dni.getBytes("utf-8");
			byte[] buf = cipher.doFinal(plainTextBytes);
			byte[] base64Bytes = Base64.encodeBase64(buf);
			base64EncryptedString = new String(base64Bytes);

		} catch (Exception ex) {
		}
		return base64EncryptedString;
	}

}
