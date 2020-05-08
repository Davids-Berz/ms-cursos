package com.mservice.app.curso.services;

import com.mservice.app.curso.models.entity.Curso;
import com.mservice.app.curso.models.repository.ICursoRepository;
import com.mservice.generic.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl extends GenericServiceImpl<Curso, ICursoRepository> implements ICursoService {

    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByAlumnoId(Long id) {
        return repository.findCursoByAlumnoId(id);
    }
}
