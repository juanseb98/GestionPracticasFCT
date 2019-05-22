package com.iescristobaldemonroy.gestorFct.util;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Clob;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComunUtil implements Serializable {

	private static final long serialVersionUID = 6876000001831375414L;

	public static boolean esEnteroPositivo(BigDecimal valor) {

		return !(valor == null || valor.toPlainString().indexOf(".") != -1 || valor.intValue() < 0);
	}

	public static boolean esNumero(String sNumero) {

		if (sNumero == null) {
			return false;
		}

		try {
			Long.parseLong(sNumero);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static boolean validarEmail(String email) {

		boolean valido = false;
		Pattern patronEmail = Pattern.compile(
				"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
		Matcher mEmail = patronEmail.matcher(email.toLowerCase());

		if (mEmail.matches()) {
			valido = true;
		}
		return valido;
	}

	public static int contadorPalabras(String texto, String palabra) {

		int contador = 0;

		while (texto.indexOf(palabra) > -1) {
			texto = texto.substring(texto.indexOf(palabra) + palabra.length(), texto.length());
			contador++;
		}

		return contador;
	}

	public static String eliminarEspaciosSaltosLineaTabulados(String cadena) {

		String sinEspacios = cadena.replace(" ", "");
		String sinSaltos = sinEspacios.replace("\r\n", "");

		return sinSaltos;
	}

	public static String throwableToString(Throwable e) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

	public static String clobToString(Clob clb) throws Exception {

		if (clb == null) {
			return null;
		}

		StringBuffer str = new StringBuffer();
		String strng;

		BufferedReader bufferRead = new BufferedReader(clb.getCharacterStream());

		while ((strng = bufferRead.readLine()) != null) {
			str.append(strng);
		}

		return str.toString();
	}

	public static Date obtenerFechaActual() {

		Calendar now = Calendar.getInstance();
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.HOUR_OF_DAY, 0);

		return now.getTime();
	}

	public static Date quitarHora(Date fecha) {

		Calendar now = Calendar.getInstance();
		now.setTime(fecha);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.HOUR_OF_DAY, 0);

		return now.getTime();
	}

	public static String quitarAcentos(String str) {

		if (str == null) {
			return null;
		}
		char[] array = str.toCharArray();
		for (int index = 0; index < array.length; index++) {
			int pos = "áéíóúàèìòùãõâêîôôäëïöüÁÉÍÓÚÀÈÌÒÙÃÕÂÊÎÔÛÄËÏÖÜ".indexOf(array[index]);
			if (pos > -1) {
				array[index] = "aeiouaeiouaoaeiooaeiouAEIOUAEIOUAOAEIOOAEIOU".charAt(pos);
			}
		}
		return new String(array);
	}

	public static String getValorAlertasMessages(String clave, Object... params) {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.alertas-messages",
				new UTF8ResourceBundleControl());
		return MessageFormat.format(resourceBundle.getString(clave), params);
	}

	public static String generarHashPassword(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		if (password == null) {
			return null;
		}

		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(password.getBytes("UTF-8"));
		byte hashBytes[] = md.digest();
		return new String(Base64.encode(hashBytes));
	}

	public static String codificarBase64(String valor) {

		if (valor == null) {
			return null;
		}

		return new String(Base64.encode(valor.getBytes()));
	}

	public static String timestampToString(Timestamp fecha) {

		String cadena = "";

		if (fecha != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(Constantes.FORMAT_DATE_COMPLETE_SIMPLEDATEFORMAT);
			cadena = sdf.format(fecha);
		}

		return cadena;

	}
}
