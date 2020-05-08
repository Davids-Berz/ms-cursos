package com.mservice.app.curso.models.entity;

import com.mservice.commons.alumnos.models.entity.Alumno;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "cursos")
public class Curso {

    public Curso(){
        this.lstAlumnos = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Alumno> lstAlumnos;

    public void addAlumno(Alumno alumno){
        this.lstAlumnos.add(alumno);
    }

    public void removeAlumno(Alumno alumno){
        this.lstAlumnos.remove(alumno);
    }

    @PrePersist
    public void prePersist(){
        this.createAt=new Date();
    }
}
