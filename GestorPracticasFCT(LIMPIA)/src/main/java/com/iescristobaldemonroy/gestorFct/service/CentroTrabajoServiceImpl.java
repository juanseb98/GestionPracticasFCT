package com.iescristobaldemonroy.gestorFct.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import com.iescristobaldemonroy.gestorFct.entity.CentroTrabajo;
import com.iescristobaldemonroy.gestorFct.repository.CentroTrabajoRepository;
import com.iescristobaldemonroy.gestorFct.util.ComunUtil;
import com.iescristobaldemonroy.gestorFct.util.Constantes;

@Service
public class CentroTrabajoServiceImpl implements CentroTrabajoService {

	private String CALLE = "calle";
	private String CODIGO_POSTAL = "codigoPostal";
	private String LOCALIDAD = "localidad";
	private String MUNICIPIO = "municipio";
	private String PRINCIPAL = "principal";
	@PersistenceContext
	EntityManager em;

	@Autowired
	private CentroTrabajoRepository repository;

	public CentroTrabajoServiceImpl() {

	}

	@Autowired
	public CentroTrabajoServiceImpl(CentroTrabajoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<CentroTrabajo> getAllCentroTrabajos() {
		List<CentroTrabajo> list = new ArrayList<CentroTrabajo>();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public List<CentroTrabajo> getCentroTrabajoByMunicipio(String municipio) {
		return repository.findByMunicipio(municipio);
	}

	@Override
	public CentroTrabajo getCentroTrabajoPrincipalByEmpresa(String denominacion) {
		return repository.findByPrincipalEmpresa(denominacion);
	}

	@Override
	public CentroTrabajo getCentroTrabajoByTelefono(String telefono) {
		return repository.findByTelefono(telefono);
	}

	@Override
	public CentroTrabajo getCentroTrabajoByDireccion(String calle, String numero) {
		return repository.findByDireccion(calle, numero);
	}

	@Override
	public boolean save(CentroTrabajo centroTrabajo) {
		try {
			repository.save(centroTrabajo);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteCentroTrabajoByTelefono(String telefono) {
		try {
			repository.delete(getCentroTrabajoByTelefono(telefono));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean exist(String cif) {
		List<CentroTrabajo> emp = repository.findByEmpresa(cif);
		if (emp.size() < 1) {
			return false;
		}
		return true;
	}

	@Override
	public List<CentroTrabajo> getCentroTrabajoByEmpresa(String cif) {
		return repository.findByEmpresa(cif);

	}

	@Override
	public List<String> getLocalidades() {
		return repository.findLocalidades();
	}

	@Override
	public List<String> getLocaMunicipio() {
		return repository.findMunicipios();
	}

	@Override
	public List<CentroTrabajo> search(String cif, String calle, String codigoPostal, String localidad, String municipio,
			Boolean principal) {
		String principalStr;
		if (principal) {
			principalStr = Constantes.PRINCIPAL_SI;
		} else {
			principalStr = Constantes.PRINCIPAL_NO;
		}
		List<CentroTrabajo> lst = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<CentroTrabajo> cq = cb.createQuery(CentroTrabajo.class);
		Root<CentroTrabajo> root = cq.from(CentroTrabajo.class);

		cq.select(root);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (StringUtils.isNotBlank(calle)) {
			predicates.add(cb.like(cb.function("QUITAR_ACENTOS", String.class, cb.lower(root.<String>get(CALLE))),
					"%" + ComunUtil.quitarAcentos(calle.toLowerCase()) + "%"));
		}

		if (StringUtils.isNotBlank(codigoPostal)) {
			predicates
					.add(cb.like(cb.function("QUITAR_ACENTOS", String.class, cb.lower(root.<String>get(CODIGO_POSTAL))),
							"%" + ComunUtil.quitarAcentos(codigoPostal.toLowerCase()) + "%"));
		}

		if (StringUtils.isNotBlank(localidad)) {
			predicates.add(cb.like(cb.function("QUITAR_ACENTOS", String.class, cb.lower(root.<String>get(LOCALIDAD))),
					"%" + ComunUtil.quitarAcentos(localidad.toLowerCase()) + "%"));
		}

		if (StringUtils.isNotBlank(municipio)) {
			predicates.add(cb.like(cb.function("QUITAR_ACENTOS", String.class, cb.lower(root.<String>get(MUNICIPIO))),
					"%" + ComunUtil.quitarAcentos(municipio.toLowerCase()) + "%"));
		}

		if (StringUtils.isNotBlank(principalStr)) {
			predicates.add(cb.like(cb.function("QUITAR_ACENTOS", String.class, cb.lower(root.<String>get(PRINCIPAL))),
					"%" + ComunUtil.quitarAcentos(principalStr.toLowerCase()) + "%"));
		}

		cq.where(predicates.toArray(new Predicate[] {}));

		lst = (List<CentroTrabajo>) em.createQuery(cq).getResultList();

		return lst;
	}

}
