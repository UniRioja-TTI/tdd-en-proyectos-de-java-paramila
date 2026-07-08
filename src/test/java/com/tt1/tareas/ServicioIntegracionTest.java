package com.tt1.tareas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServicioIntegracionTest {

    private DBStub db;
    private Repositorio repo;
    private MailerStub mailer;
    private Servicio servicio;

    @BeforeEach
    void setUp() {
        db = new DBStub();
        repo = new Repositorio(db);
        mailer = new MailerStub();
        servicio = new Servicio(repo, mailer);
    }

    @Test
    void testCrearToDoLlegaHastaLaBaseDeDatos() {
        boolean resultado = servicio.crearToDo("Aprobar TDD", LocalDate.now().plusDays(3));

        assertTrue(resultado);
        assertNotNull(db.obtenerTarea("Aprobar TDD"));
    }

    @Test
    void testAgregarEmailLlegaHastaLaBaseDeDatos() {
        servicio.agregarEmail("ana@correo.com");

        assertTrue(db.obtenerEmails().contains("ana@correo.com"));
    }

    @Test
    void testFlujoCompletoCrearMarcarYConsultar() {
        servicio.crearToDo("T1", LocalDate.now().plusDays(1));
        servicio.crearToDo("T2", LocalDate.now().plusDays(1));

        servicio.marcarFinalizada("T1");

        List<ToDo> pendientes = servicio.consultarPendientes();

        assertEquals(1, pendientes.size());
        assertEquals("T2", pendientes.get(0).getNombre());
        assertTrue(db.obtenerTarea("T1").isCompletado());
    }
}
