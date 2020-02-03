package com.jpa;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.exit;
import static java.lang.System.out;

// http://chuwiki.chuidiang.org/index.php?title=Ejemplo_sencillo_de_JPA_con_Java_SEl
public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("damPersistence");
    static EntityManager em = emf.createEntityManager();



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String consultaA="SELECT a FROM Alumno a";
        String consultaC="SELECT c FROM Clase c";
        String consultaI="SELECT i FROM Instituto i";

        for (;;) {
            System.out.println("1.-Agregar Instituto\n2.-Agregar Clase\n3.-Agregar Alumno\n4.-Comprobar numero de alumnos\n5.-Salir");
            int op = in.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Agregar Instituto");
                    addInstituto(in);
                    break;
                case 2:
                    System.out.println("Agregar Clase");
                    addClase(in);
                    break;
                case 3:
                    System.out.println("Agregar Alumno");
                    addAlumne(in);
                    break;
                case 4:
                    System.out.println("1.-Instituto\n2.-Clase");
                    int consultar = in.nextInt();
                    switch (consultar){
                        case 1:
                            TypedQuery<Instituto> q2 = em.createQuery(consultaI,Instituto.class);

                            List<Instituto> resultadosI = q2.getResultList();

                            resultadosI.forEach(System.out::println);
                            System.out.println("Elige el id de la instituto que quieres actualizar: ");
                            actualizarAlumnosInstituto(in.nextInt());
                            break;

                        case 2:
                            TypedQuery<Clase> q3 = em.createQuery(consultaC,Clase.class);

                            List<Clase> resultadosC = q3.getResultList();

                            resultadosC.forEach(System.out::println);
                            System.out.println("Elige el id de la clase que quieres actualizar: ");
                            actualizarAlumnosClase(in.nextInt());
                            break;
                    }

                    break;
                case 5:
                    em.close();
                    exit(0);
                    break;
            }
        }
    }

    public static void actualizarAlumnosClase(int idClase){
        Clase clase = em.find(Clase.class,idClase);

        TypedQuery<Alumno> q1 = em.createQuery("SELECT a FROM Alumno a",Alumno.class);

        List<Alumno> resultadosA = q1.getResultList();

        long nAlumnos = resultadosA.stream().filter(alumno -> alumno.getClaseId()==clase.getId()).count();

        em.getTransaction().begin();
        clase.setNAlumnos((int)nAlumnos);
        em.getTransaction().commit();
        System.out.println();
        System.out.println("La clase con el id "+idClase+" tiene "+nAlumnos+" alumnos");
        resultadosA.stream().filter(alumno -> alumno.getClaseId() == clase.getId()).forEach(System.out::println);
        System.out.println();

    }

    public static void actualizarAlumnosInstituto(int idInstituto){
        Instituto instituto = em.find(Instituto.class,idInstituto);

        TypedQuery<Clase> query = em.createQuery("SELECT c FROM Clase c", Clase.class);
        List<Clase> resultadosA = query.getResultList();

        AtomicInteger nAlumnos = new AtomicInteger();
        resultadosA
                .stream()
                .filter(clase -> clase.getInstitutoId() == instituto.getId())
                .forEach(clase -> {
                    nAlumnos.set(clase.getNAlumnos()+nAlumnos.get());
                });

        int numa = nAlumnos.get();
        em.getTransaction().begin();
        instituto.setNAlumnos(numa);
        em.getTransaction().commit();
        System.out.println();

        System.out.println("El instituto con el id "+idInstituto+" tiene "+numa+" alumnos");
        resultadosA.stream().filter(clase -> clase.getInstitutoId() == instituto.getId()).forEach(System.out::println);

        System.out.println();

    }



    public static void addAlumne(Scanner in){
        Alumno alumno = new Alumno();
        System.out.println("dni : ");
        alumno.setDni(in.nextInt());
        alumno.setNombre("alumno");
        System.out.println("Id de la clase: ");
        alumno.setClaseId(in.nextInt());
        em.getTransaction().begin();
        em.persist(alumno);
        em.getTransaction().commit();
    }

    public static void addInstituto(Scanner in) {
        Instituto instituto = new Instituto();

        instituto.setNombre("Instituto"+Math.random()*100+1);
        System.out.println("Id del Instituto: ");
        instituto.setId(in.nextInt());
        em.getTransaction().begin();
        em.persist(instituto);
        em.getTransaction().commit();
    }

    public static void addClase(Scanner in){
        Clase clase = new Clase();
        System.out.println("Id de la clase: ");
        clase.setId(in.nextInt());
        System.out.println("Id del Instituto: ");
        clase.setInstitutoId(in.nextInt());
        clase.setNombre("DAM654");
        clase.setRama("DAM");
        em.getTransaction().begin();
        em.persist(clase);
        em.getTransaction().commit();
    }
}