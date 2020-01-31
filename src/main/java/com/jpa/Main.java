package com.jpa;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

// http://chuwiki.chuidiang.org/index.php?title=Ejemplo_sencillo_de_JPA_con_Java_SEl
public class Main {

    EntityManagerFactory emf;
    static EntityManager em;

    public Main(){
        emf= Persistence.createEntityManagerFactory("damPersistence");
        em = emf.createEntityManager();
    }

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
                    em.getTransaction().begin();
                    System.out.println("Agregar Instituto");
                    em.persist(addInstituto(in));
                    em.getTransaction().commit();
                    break;
                case 2:
                    em.getTransaction().begin();
                    System.out.println("Agregar Clase");
                    em.persist(addClase(in));
                    em.getTransaction().commit();
                    break;
                case 3:
                    em.getTransaction().begin();
                    System.out.println("Agregar Alumno");
                    em.persist(addAlumne(in));
                    em.getTransaction().commit();
                    break;
                case 4:
                    System.out.println("1.-Clase\n3.-Instituto");
                    int consultar = in.nextInt();
                    switch (consultar){
                        case 1:
                            TypedQuery<Clase> q2 = em.createQuery(consultaC,Clase.class);

                            List<Clase> resultadosC = q2.getResultList();

                            resultadosC.forEach(System.out::println);
                            System.out.println("Elige el id de la clase que quieres actualizar: ");
                            actualizarAlumnosInstituto(in.nextInt());
                            break;

                        case 2:
                            TypedQuery<Instituto> q3 = em.createQuery(consultaI,Instituto.class);

                            List<Instituto> resultadosI = q3.getResultList();

                            resultadosI.forEach(System.out::println);
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

    public static void actualizarAlumnosInstituto(int idClase){
        Clase clase = em.find(Clase.class,idClase);

        TypedQuery<Alumno> q1 = em.createQuery("SELECT a FROM Alumno a",Alumno.class);

        List<Alumno> resultadosA = q1.getResultList();

        long nAlumnos = resultadosA.stream().filter(alumno -> alumno.getClaseId()==clase.getId()).count();

        em.getTransaction().begin();
        clase.setNAlumnos((int)nAlumnos);
        em.getTransaction().commit();
        resultadosA.forEach(System.out::println);
    }

    public void actualizarAlumnosClase(int idClase){
        Clase clase = em.find(Clase.class,idClase);

        TypedQuery<Alumno> q1 = em.createQuery("SELECT a FROM Alumno a",Alumno.class);

        List<Alumno> resultadosA = q1.getResultList();

        long nAlumnos = resultadosA.stream().filter(alumno -> alumno.getClaseId()==clase.getId()).count();

        em.getTransaction().begin();
        clase.setNAlumnos((int)nAlumnos);
        em.getTransaction().commit();

        resultadosA.forEach(System.out::println);
    }



    public static Alumno addAlumne(Scanner in){
        Alumno alumno = new Alumno();
        System.out.println("dni : ");
        alumno.setDni(in.nextInt());
        alumno.setNombre("alumno");
        System.out.println("Id de la clase: ");
        alumno.setClaseId(in.nextInt());
        return alumno;
    }

    public static Instituto addInstituto(Scanner in) {
        Instituto instituto = new Instituto();

        instituto.setNombre("Instituto"+Math.random()*100+1);
        System.out.println("Id del Instituto: ");
        instituto.setId(in.nextInt());
        return instituto;
    }

    public static Clase addClase(Scanner in){
        Clase clase = new Clase();
        System.out.println("Id de la clase: ");
        clase.setId(in.nextInt());
        System.out.println("Id del Instituto: ");
        clase.setInstitutoId(in.nextInt());
        clase.setNombre("DAM654");
        clase.setRama("DAM");
        return clase;
    }
    }