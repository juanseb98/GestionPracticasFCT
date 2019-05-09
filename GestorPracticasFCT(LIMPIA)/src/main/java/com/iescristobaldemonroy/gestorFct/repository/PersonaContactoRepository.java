package com.iescristobaldemonroy.gestorFct.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iescristobaldemonroy.gestorFct.entity.PersonaContacto;

@Repository
public interface PersonaContactoRepository extends CrudRepository<PersonaContacto, Long> {

}