package com.tt1.tareas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RepositorioIntegracionTest {

    private DBStub db;
    private Repositorio repo;

    @BeforeEach
    void setUp() {
        db = new DBStub();
        repo = new Repositorio(db);
    }

    @Test
    void testAlmacenarLlegaAlaBaseDeDatosReal() {
        ToDo tarea = new ToDo("T1", "desc", LocalDate.now());

        repo.almacenarToDo(tarea);

        assertEquals(tarea, db.obtenerTarea("T1"));
    }

    @Test
    void testMarcarCompletadoPersisteEnLaBaseDeDatosReal() {
        repo.almacenarToDo(new ToDo("T1", "desc", LocalDate.now()));

        boolean resultado = repo.marcarCompletado("T1");

        assertTrue(resultado);
        assertTrue(db.obtenerTarea("T1").isCompletado());
    }

    @Test
    void testAlmacenarEmailPersisteEnLaBaseDeDatosReal() {
        repo.almacenarEmail("ana@correo.com");

        assertTrue(db.obtenerEmails().contains("ana@correo.com"));
    }
}
