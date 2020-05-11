package com.mservice.app.curso.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
@Entity
@Table(name = "curso_alumnos")
public class CursoAlumno {

    public CursoAlumno(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alumno_id", unique = true)
    private Long alumnoId;

    @JsonIgnoreProperties(value = {"cursoAlumnos"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoAlumno that = (CursoAlumno) o;
        return Objects.equals(alumnoId, that.alumnoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumnoId);
    }
}
