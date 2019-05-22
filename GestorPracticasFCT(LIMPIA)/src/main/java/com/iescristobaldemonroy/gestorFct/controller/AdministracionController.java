package com.iescristobaldemonroy.gestorFct.controller;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iescristobaldemonroy.gestorFct.entity.Administrador;
import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.entity.Notificacion;
import com.iescristobaldemonroy.gestorFct.entity.Persona;
import com.iescristobaldemonroy.gestorFct.form.AdministradorForm;
import com.iescristobaldemonroy.gestorFct.form.EdicionAlumnoForm;
import com.iescristobaldemonroy.gestorFct.form.EdicionAlumnoTestForm;
import com.iescristobaldemonroy.gestorFct.form.EditarAlumnoForm;
import com.iescristobaldemonroy.gestorFct.service.AdministradorService;
import com.iescristobaldemonroy.gestorFct.service.AlumnoService;
import com.iescristobaldemonroy.gestorFct.service.NotificacionService;
import com.iescristobaldemonroy.gestorFct.service.PersonaService;
import com.iescristobaldemonroy.gestorFct.util.ComunUtil;
import com.iescristobaldemonroy.gestorFct.util.Constantes;

@Controller
@RequestMapping(value = "/administracion")
public class AdministracionController {

	@Autowired
	private AdministradorService administradorService;

	@Autowired
	private NotificacionService notificacionService;

	@Autowired
	private AlumnoService alumnoService;

	@Autowired
	private PersonaService personaService;

	private AdministradorForm adminForm;

	@RequestMapping(method = { RequestMethod.GET })
	public String inicio(Model model, HttpSession session) {
		Administrador admin = (Administrador) session.getAttribute("personaLog");
		if (admin != null) {
			int notificaciones = notificacionService.getTotalNotificaciones(Constantes.BOOLEAN_TRUE);
			adminForm = new AdministradorForm(admin, notificaciones);
			model.addAttribute("administradorForm", adminForm);
			return "administracion";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/notificacion", method = RequestMethod.GET)
	public String verNotificaciones(@ModelAttribute("administradorForm") AdministradorForm administradorForm,
			BindingResult result, Model model, HttpSession session) {
		try {
			if (adminForm == null) {
				result.addError(null);
			}

			if (!result.hasErrors()) {
				List<Notificacion> notificaciones = notificacionService
						.getNotificacionByEstado(Constantes.BOOLEAN_TRUE);
				try {
					model.addAttribute("listaNotificaciones", notificaciones);
				} catch (Exception e) {
					int notificacionesCount = notificacionService.getTotalNotificaciones(Constantes.BOOLEAN_TRUE);
					model.addAttribute("administradorForm", new AdministradorForm(
							(Administrador) session.getAttribute("personaLog"), notificacionesCount));
					return "administracion";
				}
			} else {
				return "error";
			}
		} catch (NullPointerException e) {
			// TODO
			System.out.println("Error no controlado");
		}
		return "notificacion";
	}

	@RequestMapping(value = "/leido", method = RequestMethod.GET)
	public String marcarNotificaciones(@RequestParam("id") String id, Model model) {

		try {
			Notificacion not = notificacionService.getNotificacionById(Integer.parseInt(id));
			not.setNueva(Constantes.BOOLEAN_FALSE);
			notificacionService.save(not);
			List<Notificacion> notificaciones = notificacionService.getNotificacionByEstado(Constantes.BOOLEAN_TRUE);
			model.addAttribute("listaNotificaciones", notificaciones);
			model.addAttribute("administradorForm", adminForm);
		} catch (Exception e) {
			return "error";
		}

		return "notificacion";
	}

	@RequestMapping(value = "/editarAlumnos", method = { RequestMethod.GET, RequestMethod.POST })
	public String editarAlumnos(@ModelAttribute EditarAlumnoForm editarAlumnoForm, AdministradorForm administradorForm,
			BindingResult result, Model model, HttpSession session) {
		try {
			if (session.getAttribute("personaLog") == null) {
				result.addError(null);
			}

			if ("limpiarFiltros".equals(editarAlumnoForm.getOperacion())) {
				editarAlumnoForm.limpiarFiltros();
			}

			if (!result.hasErrors()) {
				List<Alumno> listaAlumnos = alumnoService.search(editarAlumnoForm.getFiltroDni(),
						editarAlumnoForm.getFiltroNombre());
				System.err.println(listaAlumnos.size());
				model.addAttribute("listaAlumnos", listaAlumnos);
				model.addAttribute("administradorForm", adminForm);
			} else {
				return "error";
			}
		} catch (NullPointerException e) {
			System.out.println("Error no controlado");
		}
		return "editarAlumnos";
	}

	@RequestMapping(value = "/editarAlumnos/editar", method = RequestMethod.GET)
	public String editarAlumnos(@RequestParam(value = "id", required = false) String dni,
			AdministradorForm administradorForm, BindingResult result, Model model, HttpSession session) {
		try {
			if (session.getAttribute("personaLog") == null) {
				result.addError(null);
			}

			if (!result.hasErrors()) {
				// EdicionAlumnoForm edicionAlumnoForm = new EdicionAlumnoForm();
				EdicionAlumnoTestForm edicionAlumnoTestForm = new EdicionAlumnoTestForm();
				if (!StringUtils.isEmpty(dni) && alumnoService.exist(dni)) {
					edicionAlumnoTestForm.setPersona(personaService.getPersonaByDni(dni));
				}

				model.addAttribute("edicionAlumnoForm", edicionAlumnoTestForm);
			} else {
				return "error";
			}
		} catch (

		NullPointerException e) {
			System.out.println("Error no controlado");
		}
		return "editar";
	}

	@RequestMapping(value = "/editarAlumnos/editar", method = RequestMethod.POST)
	public String editarAlumnosSubmit(@RequestParam(value = "id", required = false) String dni,
			@ModelAttribute EdicionAlumnoTestForm edicionAlumnoForm,
			BindingResult result, Model model, HttpSession session) {
		try {
			if (session.getAttribute("personaLog") == null) {
				result.addError(null);
			}

			// if (StringUtils.isEmpty(edicionAlumnoForm.getDni())) {
			// result.rejectValue("dni", "error.campoObligatorio");
			// }
			// if (StringUtils.isEmpty(edicionAlumnoForm.getEmail())) {
			// result.rejectValue("email", "error.campoObligatorio");
			// }
			// if (StringUtils.isEmpty(edicionAlumnoForm.getNombre())) {
			// result.rejectValue("nombre", "error.campoObligatorio");
			// }
			// if (StringUtils.isEmpty(edicionAlumnoForm.getTelefono())) {
			// result.rejectValue("telefono", "error.campoObligatorio");
			// } else if (StringUtils.isNumeric(edicionAlumnoForm.getTelefono())) {
			// result.rejectValue("telefono", "error.numerico");
			// }

			if (!result.hasErrors()) {
				if (dni == null) {
					Persona personaNueva = crearNuevaPersonaAlumno(edicionAlumnoForm);
					personaService.save(personaNueva);
				} else {
					if (dni.equals(edicionAlumnoForm.getDni())) {
						Persona personaActualizada = personaService.getPersonaByDni(edicionAlumnoForm.getDni());
						Alumno alumnoActualizado = personaActualizada.getAlumno();
						alumnoActualizado.setTelefono(edicionAlumnoForm.getTelefono());
						alumnoActualizado.setEmail(edicionAlumnoForm.getEmail());
						personaActualizada.setNombre(edicionAlumnoForm.getNombre());
						personaActualizada.setAlumno(alumnoActualizado);
						personaService.save(personaActualizada);
					} else {
						personaService.delete(personaService.getPersonaByDni(dni));
						Persona personaNueva = crearNuevaPersonaAlumno(edicionAlumnoForm);
						personaService.save(personaNueva);
					}
				}

				List<Alumno> listaAlumnos = alumnoService.getAllAlumno();

				model.addAttribute("listaAlumnos", listaAlumnos);
				model.addAttribute("administradorForm", adminForm);
				model.addAttribute("editarAlumnoForm", new EditarAlumnoForm());
			} else {
				return "error";
			}
		} catch (NullPointerException e) {
			System.out.println("Error no controlado");
			return "error";
		}
		return "redirect: /GestorPracticasFCT/administracion/editarAlumnos";
	}

	private Persona crearNuevaPersonaAlumno(EdicionAlumnoTestForm edicionAlumnoForm) {
		Persona personaNueva = new Persona();
		personaNueva.setDni(edicionAlumnoForm.getDni());
		personaNueva.setNombre(edicionAlumnoForm.getNombre());
		Alumno alumnoNuevo = new Alumno();
		alumnoNuevo.setDni(edicionAlumnoForm.getDni());
		alumnoNuevo.setContrasenia(edicionAlumnoForm.getContrasenia());
		alumnoNuevo.setEmail(edicionAlumnoForm.getEmail());
		alumnoNuevo.setTelefono(edicionAlumnoForm.getTelefono());
		personaNueva.setAlumno(alumnoNuevo);
		return personaNueva;
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
