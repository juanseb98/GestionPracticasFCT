
package com.iescristobaldemonroy.gestorFct.controller;

import java.security.MessageDigest;
import java.util.Arrays;

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

import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.form.AlumnoForm;
import com.iescristobaldemonroy.gestorFct.service.AlumnoService;

@Controller
@RequestMapping(value = "/alumno")
public class AlumnoController {
	@Autowired
	private AlumnoService alumnoService;
	Alumno alumno;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String displaySortedMembers(@RequestParam(value = "pikjuihj", required = true) String dni, Model model) {
		String dniLimpio = desencriptar(dni);
		alumno = alumnoService.getAlumnoByDni(dniLimpio);
		model.addAttribute("alumnoForm", new AlumnoForm(alumno));
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
