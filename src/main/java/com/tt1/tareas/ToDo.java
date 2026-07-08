package com.tt1.tareas;

import java.time.LocalDate;

public class ToDo {
    private String nombre;
    private String descripcion;
    private LocalDate fechaLimite;
    private boolean completado;

    public ToDo(String nombre, String descripcion, LocalDate fechaLimite) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.completado = false;
    }

    public String getNombre() { return nombre; }

    public String getDescripcion() { return descripcion; }

    public LocalDate getFechaLimite() { return fechaLimite; }

    public boolean isCompletado() { return completado; }

    public void setCompletado(boolean completado) { this.completado = completado; }
}
