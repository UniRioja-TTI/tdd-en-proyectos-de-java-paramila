package com.tt1.tareas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tt1.tareas.mock.MailerMock;
import com.tt1.tareas.mock.RepositorioFake;

class ServicioTest {

    private RepositorioFake repo;
    private MailerMock mailer;
    private Servicio servicio;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        repo = new RepositorioFake();
        mailer = new MailerMock();
        servicio = new Servicio(repo, mailer);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testCrearToDoValido() {
        boolean resultado = servicio.crearToDo("Aprobar TDD", LocalDate.now().plusDays(3));

        assertTrue(resultado);
        assertNotNull(repo.encontrarToDo("Aprobar TDD"));
    }

    @Test
    void testCrearToDoConNombreVacioDevuelveFalse() {
        assertFalse(servicio.crearToDo("   ", LocalDate.now().plusDays(3)));
    }

    @Test
    void testCrearToDoConNombreNuloDevuelveFalse() {
        assertFalse(servicio.crearToDo(null, LocalDate.now().plusDays(3)));
    }

    @Test
    void testAgregarEmailValido() {
        boolean resultado = servicio.agregarEmail("ana@correo.com");

        assertTrue(resultado);
        assertTrue(repo.obtenerEmails().contains("ana@correo.com"));
    }

    @Test
    void testAgregarEmailInvalidoDevuelveFalse() {
        assertFalse(servicio.agregarEmail("correo-sin-arroba"));
    }

    @Test
    void testMarcarFinalizadaExistente() {
        servicio.crearToDo("T1", LocalDate.now().plusDays(1));

        boolean resultado = servicio.marcarFinalizada("T1");

        assertTrue(resultado);
        assertTrue(repo.encontrarToDo("T1").isCompletado());
    }

    @Test
    void testMarcarFinalizadaInexistenteDevuelveFalse() {
        assertFalse(servicio.marcarFinalizada("noExiste"));
    }

    @Test
    void testConsultarPendientesSoloDevuelveNoCompletadas() {
        servicio.crearToDo("Pendiente", LocalDate.now().plusDays(1));
        servicio.crearToDo("Hecha", LocalDate.now().plusDays(1));
        servicio.marcarFinalizada("Hecha");

        List<ToDo> pendientes = servicio.consultarPendientes();

        assertEquals(1, pendientes.size());
        assertEquals("Pendiente", pendientes.get(0).getNombre());
    }

    @Test
    void testSeEnviaAlertaCuandoHayTareaVencida() {
        servicio.agregarEmail("ana@correo.com");
        mailer.vecesLlamado = 0;
        mailer.destinatarios.clear();

        servicio.crearToDo("Vencida", LocalDate.now().minusDays(1));

        assertEquals(1, mailer.vecesLlamado);
        assertTrue(mailer.destinatarios.contains("ana@correo.com"));
    }

    @Test
    void testNoSeEnviaAlertaSiNoHayTareasVencidas() {
        servicio.agregarEmail("ana@correo.com");
        mailer.vecesLlamado = 0;

        servicio.crearToDo("EnPlazo", LocalDate.now().plusDays(5));

        assertEquals(0, mailer.vecesLlamado);
    }

    @Test
    void testSeEnviaAlertaATodaLaAgenda() {
        servicio.crearToDo("Vencida", LocalDate.now().minusDays(2));
        servicio.agregarEmail("ana@correo.com");
        servicio.agregarEmail("luis@correo.com");
        mailer.vecesLlamado = 0;

        servicio.consultarPendientes();

        assertEquals(2, mailer.vecesLlamado);
    }
}
