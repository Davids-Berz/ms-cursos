package com.mservice.app.curso.services;

import com.mservice.app.curso.models.entity.Curso;
import com.mservice.generic.services.IGenericService;

public interface ICursoService extends IGenericService<Curso> {

    Curso findCursoByAlumnoId(Long id);
}
