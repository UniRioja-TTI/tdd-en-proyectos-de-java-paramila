package com.tt1.tareas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tt1.tareas.mock.BaseDatosFake;

class RepositorioTest {

    private BaseDatosFake db;
    private Repositorio repo;

    @BeforeEach
    void setUp() {
        db = new BaseDatosFake();
        repo = new Repositorio(db);
    }

    @Test
    void testAlmacenarYEncontrarToDo() {
        ToDo tarea = new ToDo("T1", "desc", LocalDate.now());

        boolean guardado = repo.almacenarToDo(tarea);

        assertTrue(guardado);
        assertEquals(tarea, repo.encontrarToDo("T1"));
    }

    @Test
    void testEncontrarToDoInexistenteDevuelveNull() {
        assertNull(repo.encontrarToDo("noExiste"));
    }

    @Test
    void testMarcarCompletadoExistente() {
        repo.almacenarToDo(new ToDo("T1", "desc", LocalDate.now()));

        boolean resultado = repo.marcarCompletado("T1");

        assertTrue(resultado);
        assertTrue(repo.encontrarToDo("T1").isCompletado());
    }

    @Test
    void testMarcarCompletadoInexistenteDevuelveFalse() {
        boolean resultado = repo.marcarCompletado("noExiste");

        assertFalse(resultado);
    }

    @Test
    void testAlmacenarEmail() {
        boolean resultado = repo.almacenarEmail("ana@correo.com");

        assertTrue(resultado);
        assertTrue(repo.obtenerEmails().contains("ana@correo.com"));
    }
}
