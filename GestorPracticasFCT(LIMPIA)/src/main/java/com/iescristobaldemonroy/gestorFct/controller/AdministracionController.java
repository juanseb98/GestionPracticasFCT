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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iescristobaldemonroy.gestorFct.constantes.Constantes;
import com.iescristobaldemonroy.gestorFct.entity.Administrador;
import com.iescristobaldemonroy.gestorFct.entity.Interesado;
import com.iescristobaldemonroy.gestorFct.entity.Notificacion;
import com.iescristobaldemonroy.gestorFct.entity.Practica;
import com.iescristobaldemonroy.gestorFct.entity.Valoracion;
import com.iescristobaldemonroy.gestorFct.form.AdministradorForm;
import com.iescristobaldemonroy.gestorFct.form.InteresadoForm;
import com.iescristobaldemonroy.gestorFct.service.AdministradorService;
import com.iescristobaldemonroy.gestorFct.service.NotificacionService;

@Controller
@RequestMapping(value = "/administracion")
public class AdministracionController {

	@Autowired
	private AdministradorService administradorService;

	@Autowired
	private NotificacionService notificacionService;

	private Administrador admin;
	private AdministradorForm adminForm;
	private int idNotificacion;

	@RequestMapping(method = { RequestMethod.GET })
	public String inicio(@RequestParam(value = "pikjuihj", required = true) String dni, Model model) {
		String dniLimpio = desencriptar(dni);
		admin = administradorService.getAdministradorByDni(dniLimpio);
		int notificaciones = notificacionService.getTotalNotificaciones(Constantes.BOOLEAN_TRUE);
		adminForm = new AdministradorForm(admin, notificaciones);
		model.addAttribute("administradorForm", adminForm);
		return "administracion";
	}

	@RequestMapping(value = "/notificaciones", method = RequestMethod.GET)
	public String verNotificaciones(@ModelAttribute("administradorForm") AdministradorForm administradorForm,
			BindingResult result, Model model) {
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
					model.addAttribute("administradorForm", new AdministradorForm(admin, notificacionesCount));
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

	@RequestMapping(value = "/leido", method = RequestMethod.POST)
	public String marcarNotificaciones(@RequestParam(value = "id", required = true) String id, BindingResult result,
			Model model) {
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
					model.addAttribute("administradorForm", new AdministradorForm(admin, notificacionesCount));
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
