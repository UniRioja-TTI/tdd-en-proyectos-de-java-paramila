package com.tt1.tareas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class ToDoTest {

    @Test
    void testConstructorGuardaLosCampos() {
        LocalDate fecha = LocalDate.of(2026, 12, 31);

        ToDo tarea = new ToDo("Estudiar", "Repasar TDD", fecha);

        assertEquals("Estudiar", tarea.getNombre());
        assertEquals("Repasar TDD", tarea.getDescripcion());
        assertEquals(fecha, tarea.getFechaLimite());
    }

    @Test
    void testTareaNuevaNoEstaCompletada() {
        ToDo tarea = new ToDo("Estudiar", "Repasar TDD", LocalDate.now());

        assertFalse(tarea.isCompletado());
    }

    @Test
    void testSetCompletadoCambiaElEstado() {
        ToDo tarea = new ToDo("Estudiar", "Repasar TDD", LocalDate.now());

        tarea.setCompletado(true);

        assertTrue(tarea.isCompletado());
    }
}
