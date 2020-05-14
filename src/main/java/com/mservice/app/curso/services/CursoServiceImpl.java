package com.mservice.app.curso.services;

import com.mservice.app.curso.clients.IAlumnoFeingClient;
import com.mservice.app.curso.clients.IRespuestaFeingClient;
import com.mservice.app.curso.models.entity.Curso;
import com.mservice.app.curso.models.repository.ICursoRepository;
import com.mservice.commons.alumnos.models.entity.Alumno;
import com.mservice.generic.services.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl extends GenericServiceImpl<Curso, ICursoRepository> implements ICursoService {

    @Autowired
    private IRespuestaFeingClient client;

    @Autowired
    IAlumnoFeingClient clientAlumno;

    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByAlumnoId(Long id) {
        return repository.findCursoByAlumnoId(id);
    }

    @Override
    public Iterable<Long> obtenerExamenesIdConRespuestaAlumno(Long alumnoId) {
        return client.obtenerExamenesIdConRespuestaAlumno(alumnoId);
    }

    @Override
    public Iterable<Alumno> obtenerAlumnosPorCurso(Iterable<Long> ids) {
        return clientAlumno.obtenerAlumnosPorCurso(ids);
    }

    @Override
    @Transactional
    public void deleteCursoAlumnoByAlumnoId(Long id) {
        repository.deleteCursoAlumnoByAlumnoId(id);
    }
}
