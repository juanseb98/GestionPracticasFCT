
package com.iescristobaldemonroy.gestorFct.controller;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import javax.xml.bind.helpers.NotIdentifiableEventImpl;

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

import com.iescristobaldemonroy.gestorFct.entity.Administrador;
import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.entity.Notificacion;
import com.iescristobaldemonroy.gestorFct.entity.Practica;
import com.iescristobaldemonroy.gestorFct.entity.Valoracion;
import com.iescristobaldemonroy.gestorFct.form.AlumnoForm;
import com.iescristobaldemonroy.gestorFct.service.AlumnoService;
import com.iescristobaldemonroy.gestorFct.service.NotificacionService;
import com.iescristobaldemonroy.gestorFct.service.PersonaService;
import com.iescristobaldemonroy.gestorFct.service.PracticaService;
import com.iescristobaldemonroy.gestorFct.service.ValoracionService;
import com.iescristobaldemonroy.gestorFct.util.Constantes;

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

	@Autowired
	private NotificacionService notificacionService;

	private Alumno alumno;
	private Practica practicaSeleccionada;
	private int idPractica;

	@RequestMapping(method = { RequestMethod.GET })
	public String inicio(Model model, HttpSession session) {
		Alumno alumno = (Alumno) session.getAttribute("personaLog");
		if (alumno != null) {
			model.addAttribute("alumnoForm", new AlumnoForm(alumno));
			alumno.setPracticas(practicaService.getPracticaByAlumno(alumno.getDni()));
			return "alumno";
		} else {
			return "error";
		}

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
	public String saveValoracionSubmit(@ModelAttribute("valoracionAlumno") Valoracion valoracion, BindingResult result,
			Model model) {
		try {
			valoracion.setPractica(practicaSeleccionada);
			valoracion.setPersona(personaService.getPersonaByDni(alumno.getDni()));
			if (!result.hasErrors()) {
				try {
					valoracionesService.save(valoracion);

					Notificacion notificacion = new Notificacion(Constantes.MENSAJE_NUEVA_VALORACION,
							Constantes.BOOLEAN_TRUE, Constantes.NOTIFICACION_NUEVA_VALORACION, null, valoracionesService
									.getValoracionByPracticaAndPersona(practicaSeleccionada.getId(), alumno.getDni()));

					notificacionService.save(notificacion);

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

	@RequestMapping(value = "/permanencia", method = RequestMethod.GET)
	public String marcarNotificaciones(@RequestParam("id") String id, Model model) {

		try {
			model.addAttribute("practicaSeleccionada", practicaSeleccionada);
		} catch (Exception e) {
			return "error";
		}

		return "permanencia";
	}

	@RequestMapping(value = "/permanencia", method = RequestMethod.POST)
	public String marcarNotificacionesSubmit(@RequestParam("id") String id, Model model,
			@ModelAttribute Practica practicaSeleccionada) {

		try {
			Practica practicaActualizada = practicaService.getPracticaById(practicaSeleccionada.getId());
			practicaActualizada.setPermanencia(practicaSeleccionada.getPermanencia());
			practicaService.save(practicaActualizada);
			model.addAttribute("practicaSeleccionada", practicaActualizada);
		} catch (Exception e) {
			return "error";
		}

		return "permanencia";
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
			System.out.println("Error");
		}
		return base64EncryptedString;
	}

}
