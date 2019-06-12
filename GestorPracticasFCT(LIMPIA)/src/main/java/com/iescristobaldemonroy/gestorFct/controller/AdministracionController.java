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
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iescristobaldemonroy.gestorFct.entity.Administrador;
import com.iescristobaldemonroy.gestorFct.entity.Alumno;
import com.iescristobaldemonroy.gestorFct.entity.CentroTrabajo;
import com.iescristobaldemonroy.gestorFct.entity.Empresa;
import com.iescristobaldemonroy.gestorFct.entity.Notificacion;
import com.iescristobaldemonroy.gestorFct.entity.Persona;
import com.iescristobaldemonroy.gestorFct.form.AdministradorForm;
import com.iescristobaldemonroy.gestorFct.form.EdicionAlumnoTestForm;
import com.iescristobaldemonroy.gestorFct.form.EdicionCentroTrabajoForm;
import com.iescristobaldemonroy.gestorFct.form.EdicionEmpresaForm;
import com.iescristobaldemonroy.gestorFct.form.EditarAlumnoForm;
import com.iescristobaldemonroy.gestorFct.form.EditarCentroTrabajoForm;
import com.iescristobaldemonroy.gestorFct.form.EditarEmpresaForm;
import com.iescristobaldemonroy.gestorFct.service.AdministradorService;
import com.iescristobaldemonroy.gestorFct.service.AlumnoService;
import com.iescristobaldemonroy.gestorFct.service.CentroTrabajoService;
import com.iescristobaldemonroy.gestorFct.service.EmpresaService;
import com.iescristobaldemonroy.gestorFct.service.NotificacionService;
import com.iescristobaldemonroy.gestorFct.service.PersonaService;
import com.iescristobaldemonroy.gestorFct.util.Constantes;

@Controller
@RequestMapping(value = "/administracion")
public class AdministracionController {

	@Autowired
	private AdministradorService administradorService;

	@Autowired
	private NotificacionService notificacionService;

	@Autowired
	private CentroTrabajoService centroTrabajoService;

	@Autowired
	private AlumnoService alumnoService;

	@Autowired
	private EmpresaService empresaService;

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
						editarAlumnoForm.getFiltroNombre(), editarAlumnoForm.getFiltroAnio());
				System.err.println(listaAlumnos.size());
				model.addAttribute("listaAlumnos", listaAlumnos);

				List<String> listaAnios = alumnoService.getAnios();
				model.addAttribute("listaAnios", listaAnios);
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
			@ModelAttribute EdicionAlumnoTestForm edicionAlumnoForm, BindingResult result, Model model,
			HttpSession session) {
		try {
			if (session.getAttribute("personaLog") == null) {
				result.addError(null);
			}

			if (StringUtils.isEmpty(edicionAlumnoForm.getDni())) {
				result.rejectValue("dni", "error.campoObligatorio");
			} else if (!edicionAlumnoForm.getDni().matches("\\d{8}\\D")) {
				result.rejectValue("dni", "error.campoObligatorio");
			}
			if (StringUtils.isEmpty(edicionAlumnoForm.getEmail())) {
				result.rejectValue("email", "error.campoObligatorio");
			}
			if (StringUtils.isEmpty(edicionAlumnoForm.getNombre())) {
				result.rejectValue("nombre", "error.campoObligatorio");
			}
			if (StringUtils.isEmpty(edicionAlumnoForm.getTelefono())) {
				result.rejectValue("telefono", "error.campoObligatorio");
			} else {
				try {
					Integer.parseInt(edicionAlumnoForm.getTelefono());
				} catch (NumberFormatException e) {
					result.rejectValue("telefono", "error.numerico");
				}

			}

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
				model.addAttribute("edicionAlumnoForm", edicionAlumnoForm);
				return "editar";
			}
		} catch (NullPointerException e) {
			System.out.println("Error no controlado");
			return "error";
		}
		return "redirect: /GestorPracticasFCT/administracion/editarAlumnos";
	}

	@RequestMapping(value = "/editarEmpresas", method = { RequestMethod.GET, RequestMethod.POST })
	public String editarEmpresas(@ModelAttribute EditarEmpresaForm editarEmpresaForm,
			AdministradorForm administradorForm, BindingResult result, Model model, HttpSession session) {
		try {
			if (session.getAttribute("personaLog") == null) {
				result.addError(null);
			}

			if ("limpiarFiltros".equals(editarEmpresaForm.getOperacion())) {
				editarEmpresaForm.limpiarFiltros();
			}

			if (!result.hasErrors()) {

				List<Empresa> listaEmpresas = empresaService.search(editarEmpresaForm.getFiltroCif(),
						editarEmpresaForm.getFiltroDenominacion());
				System.err.println(listaEmpresas.size());
				model.addAttribute("editarCentroTrabajoForm", new EditarCentroTrabajoForm());
				model.addAttribute("listaEmpresas", listaEmpresas);
				model.addAttribute("administradorForm", adminForm);
			} else {
				return "error";
			}
		} catch (NullPointerException e) {
			System.out.println("Error no controlado");
		}
		return "editarEmpresas";
	}

	@RequestMapping(value = "/editarEmpresas/editar", method = RequestMethod.GET)
	public String editarEmpresas(@RequestParam(value = "id", required = false) String cif,
			AdministradorForm administradorForm, BindingResult result, Model model, HttpSession session) {
		try {
			if (session.getAttribute("personaLog") == null) {
				result.addError(null);
			}

			if (!result.hasErrors()) {
				EdicionEmpresaForm edicionEmpresaForm = new EdicionEmpresaForm();
				if (!StringUtils.isEmpty(cif) && empresaService.existByCif(cif)) {
					edicionEmpresaForm.setEmpresa(empresaService.getEmpresaByCif(cif));
				}

				model.addAttribute("edicionEmpresaForm", edicionEmpresaForm);
			} else {
				return "error";
			}
		} catch (

		NullPointerException e) {
			System.out.println("Error no controlado");
		}
		return "editarE";
	}

	@RequestMapping(value = "/editarEmpresas/editar", method = RequestMethod.POST)
	public String editarEmpresasSubmit(@RequestParam(value = "id", required = false) String cif,
			@ModelAttribute EdicionEmpresaForm edicionEmpresaForm, @ModelAttribute EditarEmpresaForm editarEmpresaForm,
			BindingResult result, Model model, HttpSession session) {
		try {
			if (session.getAttribute("personaLog") == null) {
				result.addError(null);
			}

			if (StringUtils.isEmpty(edicionEmpresaForm.getCif())) {
				result.rejectValue("cif", "error.campoObligatorio");
			} else if (!edicionEmpresaForm.getCif().matches("\\D\\d{8}")) {
				result.rejectValue("cif", "error.campoObligatorio");
			}
			if (StringUtils.isEmpty(edicionEmpresaForm.getDenominacion())) {
				result.rejectValue("denominacion", "error.campoObligatorio");
			}
			if (StringUtils.isEmpty(edicionEmpresaForm.getTelefono())) {
				result.rejectValue("telefono", "error.campoObligatorio");
			} else {
				try {
					Integer.parseInt(edicionEmpresaForm.getTelefono());
				} catch (NumberFormatException e) {
					result.rejectValue("telefono", "error.numerico");
				}

			}

			if (!result.hasErrors()) {
				Empresa empresa;
				if (cif == null) {
					empresa = new Empresa();
					darDatosEmpresa(edicionEmpresaForm, empresa);
				} else {
					if (!cif.equals(edicionEmpresaForm.getCif())) {
						empresa = empresaService.getEmpresaByCif(cif);
						empresaService.deleteEmpresaBycif(cif);
					} else {
						empresa = empresaService.getEmpresaByCif(cif);
					}

					darDatosEmpresa(edicionEmpresaForm, empresa);
				}
				empresaService.save(empresa);

				List<Empresa> listaEmpresas = empresaService.search(editarEmpresaForm.getFiltroCif(),
						editarEmpresaForm.getFiltroDenominacion());
				model.addAttribute("editarCentroTrabajoForm", new EditarCentroTrabajoForm());
				model.addAttribute("listaEmpresas", listaEmpresas);
				model.addAttribute("administradorForm", adminForm);
			} else {
				return "editarE";
			}
		} catch (NullPointerException e) {
			System.out.println("Error no controlado");
			return "error";
		}
		return "redirect: /GestorPracticasFCT/administracion/editarEmpresas";
	}

	private void darDatosEmpresa(EdicionEmpresaForm edicionEmpresaForm, Empresa empresa) {
		empresa.setCif(edicionEmpresaForm.getCif());
		empresa.setDenominacion(edicionEmpresaForm.getDenominacion());
		empresa.setTelefono(edicionEmpresaForm.getTelefono());
	}

	@RequestMapping(value = "/editarCentroTrabajo", method = { RequestMethod.GET, RequestMethod.POST })
	public String editarCentroTrabajo(@RequestParam(value = "id", required = true) String cif,
			@ModelAttribute EditarCentroTrabajoForm editarCentroTrabajoForm, AdministradorForm administradorForm,
			BindingResult result, Model model, HttpSession session) {
		try {
			if (session.getAttribute("personaLog") == null) {
				result.addError(null);
			}
			if ("limpiarFiltros".equals(editarCentroTrabajoForm.getOperacion())) {
				editarCentroTrabajoForm.limpiarFiltros();
			}

			if (!result.hasErrors()) {
				List<CentroTrabajo> listacentrosTrabajo;
				if (editarCentroTrabajoForm.getInicio().equals("S")) {
					listacentrosTrabajo = centroTrabajoService.getCentroTrabajoByEmpresa(cif);
				} else {
					listacentrosTrabajo = centroTrabajoService.search(cif, editarCentroTrabajoForm.getFiltroCalle(),
							editarCentroTrabajoForm.getFiltroCodigoPostal(),
							editarCentroTrabajoForm.getFiltroLocalidad(), editarCentroTrabajoForm.getFiltroMunicipio(),
							editarCentroTrabajoForm.getFiltroPrincipal());
				}

				Empresa empresa = empresaService.getEmpresaByCif(cif);
				model.addAttribute("empresa", empresa);

				session.setAttribute("cif", cif);

				model.addAttribute("listaCentrosTrabajo", listacentrosTrabajo);

				List<String> municipios = centroTrabajoService.getLocaMunicipio(cif);
				model.addAttribute("listaMunicipios", municipios);
				List<String> localidades = centroTrabajoService.getLocalidades(cif);
				model.addAttribute("listaLocalidades", localidades);
			} else {
				return "error";
			}
		} catch (

		NullPointerException e) {
			System.out.println("Error no controlado");
		}
		return "editarCentroTrabajo";
	}

	@RequestMapping(value = "/editarCentroTrabajo/editarC", method = RequestMethod.GET)
	public String editarCentroTrabajo(@RequestParam(value = "id", required = false) String id,
			AdministradorForm administradorForm, BindingResult result, Model model, HttpSession session) {
		try {
			if (session.getAttribute("personaLog") == null) {
				result.addError(null);
			}
			model.addAttribute("cif", session.getAttribute("cif"));
			// session.removeAttribute("cif");
			if (!result.hasErrors()) {
				// EdicionAlumnoForm edicionAlumnoForm = new EdicionAlumnoForm();
				EdicionCentroTrabajoForm edicionCentroTrabajoForm = new EdicionCentroTrabajoForm();
				if (!StringUtils.isEmpty(id) && centroTrabajoService.exist(id)) {
					edicionCentroTrabajoForm.setCentroTrabajo(centroTrabajoService.getCentroTrabajoByIdC(id));
				}
				model.addAttribute("edicionCentroTrabajoForm", edicionCentroTrabajoForm);
			} else {
				return "error";
			}
		} catch (

		NullPointerException e) {
			System.out.println("Error no controlado");
		}
		return "editarC";
	}

	@RequestMapping(value = "/editarCentroTrabajo/editarC", method = RequestMethod.POST)
	public String editarCentroTrabajoSubmit(@RequestParam(value = "id", required = false) String id,
			@ModelAttribute EdicionCentroTrabajoForm edicionCentroTrabajoForm, BindingResult result, Model model,
			HttpSession session) {
		String cif = (String) session.getAttribute("cif");
		model.addAttribute("cif", cif);
		try {

			if (session.getAttribute("personaLog") == null) {
				result.addError(null);
			}

			if (StringUtils.isEmpty(edicionCentroTrabajoForm.getProvincia())) {
				result.rejectValue("provincia", "error.campoObligatorio");
			} else if (edicionCentroTrabajoForm.getProvincia().length() > 30) {
				result.rejectValue("provincia", "error.datoMuyLargo");
			}
			if (StringUtils.isEmpty(edicionCentroTrabajoForm.getDescripcion())) {
				result.rejectValue("descripcion", "error.campoObligatorio");
			} else if (edicionCentroTrabajoForm.getDescripcion().length() > 100) {
				result.rejectValue("descripcion", "error.datoMuyLargo");
			}
			if (StringUtils.isEmpty(edicionCentroTrabajoForm.getMunicipio())) {
				result.rejectValue("municipio", "error.campoObligatorio");
			} else if (edicionCentroTrabajoForm.getMunicipio().length() > 30) {
				result.rejectValue("municipio", "error.datoMuyLargo");
			}
			if (StringUtils.isEmpty(edicionCentroTrabajoForm.getLocalidad())) {
				result.rejectValue("localidad", "error.campoObligatorio");
			} else if (edicionCentroTrabajoForm.getLocalidad().length() > 30) {
				result.rejectValue("localidad", "error.datoMuyLargo");
			}
			if (StringUtils.isEmpty(edicionCentroTrabajoForm.getCodigoPostal())) {
				result.rejectValue("codigoPostal", "error.campoObligatorio");
			} else if (edicionCentroTrabajoForm.getCodigoPostal().length() > 5) {
				result.rejectValue("codigoPostal", "error.datoMuyLargo");
			}
			if (StringUtils.isEmpty(edicionCentroTrabajoForm.getCalle())) {
				result.rejectValue("calle", "error.campoObligatorio");
			} else if (edicionCentroTrabajoForm.getCalle().length() > 20) {
				result.rejectValue("calle", "error.datoMuyLargo");
			}

			if (!result.hasErrors()) {
				CentroTrabajo centro;
				if (StringUtils.isNotEmpty(id)) {
					centro = centroTrabajoService.getCentroTrabajoByIdC(id);
					addValoresCentroTrabajo(edicionCentroTrabajoForm, centro);

				} else {
					centro = new CentroTrabajo();
					addValoresCentroTrabajo(edicionCentroTrabajoForm, centro);
					Empresa empresa = empresaService.getEmpresaByCif(cif);
					centro.setEmpresa(empresa);
				}

				if (!centroTrabajoService.save(centro)) {
					String error = "Error";
					model.addAttribute("error", error);
					return "editarC";
				}

			} else {
				return "editarC";
			}
		} catch (NullPointerException e) {
			System.out.println("Error no controlado");
			return "error";
		}

		model.addAttribute("editarCentroTrabajoForm", new EditarCentroTrabajoForm());
		return "redirect: /GestorPracticasFCT/administracion/editarCentroTrabajo?id=" + cif;
	}

	@RequestMapping(value = "/editarCentroTrabajo/eliminar", method = RequestMethod.GET)
	public String eliminarCentroTrabajo(@RequestParam(value = "id", required = true) String id,
			AdministradorForm administradorForm, @ModelAttribute EditarCentroTrabajoForm editarCentroTrabajoForm,
			BindingResult result, Model model, HttpSession session) {
		try {
			if (session.getAttribute("personaLog") == null) {
				result.addError(null);
			}
			String cif = (String) session.getAttribute("cif");
			model.addAttribute("cif", cif);
			if (!result.hasErrors()) {
				EdicionCentroTrabajoForm edicionCentroTrabajoForm = new EdicionCentroTrabajoForm();
				if (!StringUtils.isEmpty(id) && centroTrabajoService.exist(id)) {
					centroTrabajoService.delete(id);
				}
				Empresa empresa = empresaService.getEmpresaByCif(cif);
				model.addAttribute("empresa", empresa);
				if (session.getAttribute("cif") == null) {
					session.setAttribute("cif", cif);
				}
				List<CentroTrabajo> listaCentros = centroTrabajoService.getCentroTrabajoByEmpresa(cif);
				model.addAttribute("listaCentrosTrabajo", listaCentros);

				List<String> municipios = centroTrabajoService.getLocaMunicipio(cif);
				model.addAttribute("listaMunicipios", municipios);
				List<String> localidades = centroTrabajoService.getLocalidades(cif);
				model.addAttribute("listaLocalidades", localidades);
			} else {
				return "error";
			}
		} catch (NullPointerException e) {
			System.out.println("Error no controlado");
		}
		return "redirect: /GestorPracticasFCT/administracion/editarCentroTrabajo";
	}

	private Persona crearNuevaPersonaAlumno(EdicionAlumnoTestForm edicionAlumnoForm) {
		Persona personaNueva = new Persona();
		personaNueva.setDni(edicionAlumnoForm.getDni());
		personaNueva.setNombre(edicionAlumnoForm.getNombre());
		Alumno alumnoNuevo = new Alumno();
		alumnoNuevo.setDni(edicionAlumnoForm.getDni());

		StringBuilder str = new StringBuilder();
		str.append(personaNueva.getNombre().substring(0, 2).toLowerCase());
		str.append(personaNueva.getDni().substring(0, 5));
		alumnoNuevo.setContrasenia(str.toString());
		alumnoNuevo.setEmail(edicionAlumnoForm.getEmail());
		alumnoNuevo.setAnioEstudio(edicionAlumnoForm.getAnioEstudio());
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

	private void addValoresCentroTrabajo(EdicionCentroTrabajoForm edicionCentroTrabajoForm, CentroTrabajo centro) {
		centro.setCalle(edicionCentroTrabajoForm.getCalle());
		centro.setCodigoPostal(edicionCentroTrabajoForm.getCodigoPostal());
		centro.setDescripcion(edicionCentroTrabajoForm.getDescripcion());
		centro.setEmail(edicionCentroTrabajoForm.getEmail());
		centro.setEscalera(edicionCentroTrabajoForm.getEscalera());
		centro.setLetra(edicionCentroTrabajoForm.getLetra());
		centro.setLocalidad(edicionCentroTrabajoForm.getLocalidad());
		centro.setMunicipio(edicionCentroTrabajoForm.getMunicipio());
		centro.setNumero(edicionCentroTrabajoForm.getNumero());
		centro.setPiso(edicionCentroTrabajoForm.getPiso());
		centro.setPrincipal(edicionCentroTrabajoForm.getPrincipal());
		centro.setProvincia(edicionCentroTrabajoForm.getProvincia());
		centro.setTelefono(edicionCentroTrabajoForm.getTelefono());
		centro.setTipoVia(edicionCentroTrabajoForm.getTipoVia());
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
