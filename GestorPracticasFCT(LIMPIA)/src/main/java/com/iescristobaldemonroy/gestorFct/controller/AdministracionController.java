package com.iescristobaldemonroy.gestorFct.controller;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iescristobaldemonroy.gestorFct.constantes.Constantes;
import com.iescristobaldemonroy.gestorFct.entity.Administrador;
import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.entity.Notificacion;
import com.iescristobaldemonroy.gestorFct.form.AdministradorForm;
import com.iescristobaldemonroy.gestorFct.form.EditarAlumnoForm;
import com.iescristobaldemonroy.gestorFct.service.AdministradorService;
import com.iescristobaldemonroy.gestorFct.service.AlumnoService;
import com.iescristobaldemonroy.gestorFct.service.NotificacionService;

@Controller
@RequestMapping(value = "/administracion")
public class AdministracionController {

	@Autowired
	private AdministradorService administradorService;

	@Autowired
	private NotificacionService notificacionService;

	@Autowired
	private AlumnoService alumnoService;

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

			if (!result.hasErrors()) {
				List<Alumno> listaAlumnos;

				// model.addAttribute("editarAlumnoForm", new EditarAlumnoForm());

				listaAlumnos = alumnoService.search(editarAlumnoForm.getFiltroDni(),
						editarAlumnoForm.getFiltroNombre());

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
