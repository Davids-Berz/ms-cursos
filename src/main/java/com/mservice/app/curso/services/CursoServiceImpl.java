package com.mservice.app.curso.services;

import com.mservice.app.curso.models.entity.Curso;
import com.mservice.app.curso.models.repository.ICursoRepository;
import com.mservice.generic.services.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl extends GenericServiceImpl<Curso, ICursoRepository> implements ICursoService {

}
