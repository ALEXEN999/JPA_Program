package com.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

// http://chuwiki.chuidiang.org/index.php?title=Ejemplo_sencillo_de_JPA_con_Java_SEl
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("damPersistence");
        EntityManager em = emf.createEntityManager();
        // read the existing entries and write to console
        Instituto instituto = new Instituto();
        instituto.setId(1);
        instituto.setNAlumnos("1");
        instituto.setNombre("Institut Puig Castellar");

        Clase clase = new Clase();
        clase.setId(1);
        clase.setInstitutoId(1);
        clase.setNAlumnos(1);
        clase.setNombre("2n-DAM");
        clase.setRama("DAM");

        Alumno pratik = new Alumno();
        pratik.setDni(12345678);
        pratik.setNombre("pratik");
        pratik.setClaseId(1);

        em.getTransaction().begin();
        em.persist(instituto);
        em.persist(clase);
        em.persist(pratik);
        em.getTransaction().commit();
        em.close();
    }
}