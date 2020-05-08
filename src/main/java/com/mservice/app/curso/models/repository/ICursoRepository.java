package com.mservice.app.curso.models.repository;

import com.mservice.app.curso.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface ICursoRepository extends CrudRepository<Curso, Long> {
}
