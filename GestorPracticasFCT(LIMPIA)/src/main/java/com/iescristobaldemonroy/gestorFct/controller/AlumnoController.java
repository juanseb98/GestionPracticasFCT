
package com.iescristobaldemonroy.gestorFct.controller;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.entity.Practica;
import com.iescristobaldemonroy.gestorFct.entity.Valoracion;
import com.iescristobaldemonroy.gestorFct.form.AlumnoForm;
import com.iescristobaldemonroy.gestorFct.service.AlumnoService;
import com.iescristobaldemonroy.gestorFct.service.PersonaService;
import com.iescristobaldemonroy.gestorFct.service.PracticaService;
import com.iescristobaldemonroy.gestorFct.service.ValoracionService;

@Controller
@RequestMapping(value = "/alumno")
public class AlumnoController {
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private PracticaService practicaService;

	@Autowired
	private ValoracionService valoracionesService;

	@Autowired
	private PersonaService personaService;

	Alumno alumno;
	Practica practicaSeleccionada;
	int idPractica;

	@RequestMapping(method = { RequestMethod.GET })
	public String displaySortedMembers(@RequestParam(value = "pikjuihj", required = true) String dni, Model model) {
		String dniLimpio = desencriptar(dni);
		alumno = alumnoService.getAlumnoByDni(dniLimpio);
		alumno.setPracticas(practicaService.getPracticaByAlumno(dniLimpio));
		model.addAttribute("alumnoForm", new AlumnoForm(alumno));
		return "alumno";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String obtenerPracticaSeleccionada(@ModelAttribute("alumnoForm") AlumnoForm alumnoForm, BindingResult result,
			Model model) {
		try {
			alumnoForm.setPracticas(practicaService.getPracticaByAlumno(alumno.getDni()));

			if (!result.hasErrors()) {
				try {
					idPractica = Integer.parseInt(alumnoForm.getPracticaSelected());
					practicaSeleccionada = practicaService
							.getPracticaById(Integer.parseInt(alumnoForm.getPracticaSelected()));
					List<Valoracion> valoraciones = valoracionesService.getValoracionByPractica(idPractica);

					practicaSeleccionada.setValoracions(valoraciones);

					model.addAttribute("practicaSeleccionada", practicaSeleccionada);
				} catch (Exception e) {
					return "alumno";
				}
			}
		} catch (NullPointerException e) {
			// TODO
			System.out.println("Error no controlado");
		}
		return "alumno";
	}

	@RequestMapping("/valoracion")
	public String verValoracion(@ModelAttribute("practicaSeleccionada") Practica practica, BindingResult result,
			Model model) {
		try {
			Valoracion valoracioneAlumno = valoracionesService.getValoracionByPracticaAndPersona(idPractica,
					alumno.getDni());

			if (!result.hasErrors()) {
				try {
					if (valoracioneAlumno != null) {
						model.addAttribute("valoracionAlumno", valoracioneAlumno);
					} else {
						model.addAttribute("valoracionAlumno", new Valoracion());
					}
				} catch (Exception e) {
					return "valoracion";
				}
			}
		} catch (NullPointerException e) {
			// TODO
			System.out.println("Error no controlado");
		}
		return "valoracion";
	}

	@RequestMapping(value = "/valoracion", method = RequestMethod.POST)
	public String saveValoracion(@ModelAttribute("valoracionAlumno") Valoracion valoracion, BindingResult result,
			Model model) {
		try {
			valoracion.setPractica(practicaSeleccionada);
			valoracion.setPersona(personaService.getPersonaByDni(alumno.getDni()));
			if (!result.hasErrors()) {
				try {
					valoracionesService.save(valoracion);
				} catch (Exception e) {
					return "valoracion";
				}
			}
		} catch (NullPointerException e) {
			// TODO
			System.out.println("Error no controlado");
		}
		return "valoracion";
	}

	public static String desencriptar(String textoEncriptado) {

		String secretKey = "qualityinfosolutions"; // llave para desenciptar datos
		String base64EncryptedString = "";

		try {
			byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");

			Cipher decipher = Cipher.getInstance("DESede");
			decipher.init(Cipher.DECRYPT_MODE, key);

			byte[] plainText = decipher.doFinal(message);

			base64EncryptedString = new String(plainText, "UTF-8");

		} catch (Exception ex) {
		}
		return base64EncryptedString;
	}

}
