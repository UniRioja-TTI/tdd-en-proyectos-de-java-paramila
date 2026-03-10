package com.tt1.tareas;

import java.time.LocalDate;

/**
 * Representa una tarea (ToDo) dentro del sistema.
 * Contiene toda la información necesaria para gestionar una actividad,
 * incluyendo su nombre, descripción, fecha límite y si ha sido completada o no.
 */
public class ToDo {
    private String nombre;
    private String descripcion;
    private LocalDate fechaLimite;
    private boolean completado;

    /**
     * Construye una nueva tarea. Por defecto, al crearse, la tarea no está completada.
     * * @param nombre El nombre o título identificativo de la tarea.
     * @param descripcion La descripción detallada de lo que hay que hacer.
     * @param fechaLimite La fecha tope en la que debe completarse la tarea.
     */
    public ToDo(String nombre, String descripcion, LocalDate fechaLimite) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.completado = false; // Por defecto, una tarea no está completada
    }

    /**
     * Obtiene el nombre de la tarea.
     * @return El nombre identificativo.
     */
    public String getNombre() { return nombre; }

    /**
     * Obtiene la descripción de la tarea.
     * @return El texto descriptivo.
     */
    public String getDescripcion() { return descripcion; }

    /**
     * Obtiene la fecha límite de la tarea.
     * @return Objeto LocalDate que representa la fecha tope.
     */
    public LocalDate getFechaLimite() { return fechaLimite; }

    /**
     * Comprueba si la tarea ha sido finalizada.
     * @return true si la tarea está completada, false en caso contrario.
     */
    public boolean isCompletado() { return completado; }

    /**
     * Modifica el estado de completado de la tarea.
     * @param completado El nuevo estado de la tarea (true para completada).
     */
    public void setCompletado(boolean completado) { this.completado = completado; }
}