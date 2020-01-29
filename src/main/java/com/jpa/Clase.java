package com.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Clase")
public class Clase {
    @Id
    @Column(name = "id", insertable = false, nullable = false)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "rama")
    private String rama;

    @Column(name = "n_Alumnos")
    private int nAlumnos;

    @Column(name = "instituto_id", nullable = false)
    private int institutoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRama() {
        return rama;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    public int getNAlumnos() {
        return nAlumnos;
    }

    public void setNAlumnos(int nAlumnos) {
        this.nAlumnos = nAlumnos;
    }

    public int getInstitutoId() {
        return institutoId;
    }

    public void setInstitutoId(int institutoId) {
        this.institutoId = institutoId;
    }

    public String toString() {
      return "Clase{id=" + id + 
        ", nombre=" + nombre + 
        ", rama=" + rama + 
        ", nAlumnos=" + nAlumnos + 
        ", institutoId=" + institutoId + 
        "}";
    }
}