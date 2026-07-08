package com.tt1.tareas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DBStubTest {

    private DBStub db;

    @BeforeEach
    void setUp() {
        db = new DBStub();
    }

    @Test
    void testGuardarYObtenerTarea() {
        ToDo tarea = new ToDo("T1", "desc", LocalDate.now());

        db.guardarTarea(tarea);

        assertEquals(tarea, db.obtenerTarea("T1"));
    }

    @Test
    void testObtenerTareaInexistenteDevuelveNull() {
        assertNull(db.obtenerTarea("noExiste"));
    }

    @Test
    void testActualizarTarea() {
        ToDo tarea = new ToDo("T1", "desc", LocalDate.now());
        db.guardarTarea(tarea);

        tarea.setCompletado(true);
        db.actualizarTarea(tarea);

        assertTrue(db.obtenerTarea("T1").isCompletado());
    }

    @Test
    void testBorrarTarea() {
        db.guardarTarea(new ToDo("T1", "desc", LocalDate.now()));

        db.borrarTarea("T1");

        assertNull(db.obtenerTarea("T1"));
    }

    @Test
    void testObtenerTodasLasTareas() {
        db.guardarTarea(new ToDo("T1", "desc", LocalDate.now()));
        db.guardarTarea(new ToDo("T2", "desc", LocalDate.now()));

        List<ToDo> todas = db.obtenerTodasLasTareas();

        assertEquals(2, todas.size());
    }

    @Test
    void testGuardarYObtenerEmails() {
        db.guardarEmail("ana@correo.com");
        db.guardarEmail("luis@correo.com");

        assertEquals(2, db.obtenerEmails().size());
        assertTrue(db.obtenerEmails().contains("ana@correo.com"));
    }

    @Test
    void testEmailsNoSeDuplican() {
        db.guardarEmail("ana@correo.com");
        db.guardarEmail("ana@correo.com");

        assertEquals(1, db.obtenerEmails().size());
    }
}
