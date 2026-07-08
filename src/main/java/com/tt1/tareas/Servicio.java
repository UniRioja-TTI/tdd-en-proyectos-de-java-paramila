package com.tt1.tareas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Servicio {
    private IRepositorio repositorio;
    private IMailer mailer;

    public Servicio(IRepositorio repositorio, IMailer mailer) {
        this.repositorio = repositorio;
        this.mailer = mailer;
    }

    public boolean crearToDo(String nombre, LocalDate fechaLimite) {
        if (nombre == null || nombre.trim().isEmpty()) return false;
        ToDo nuevaTarea = new ToDo(nombre, "Sin descripción", fechaLimite);
        repositorio.almacenarToDo(nuevaTarea);
        comprobarFechasYAlertar();
        return true;
    }

    public boolean agregarEmail(String email) {
        if (email == null || !email.contains("@")) return false;
        repositorio.almacenarEmail(email);
        comprobarFechasYAlertar();
        return true;
    }

    public boolean marcarFinalizada(String nombre) {
        boolean exito = repositorio.marcarCompletado(nombre);
        comprobarFechasYAlertar();
        return exito;
    }

    public List<ToDo> consultarPendientes() {
        comprobarFechasYAlertar();
        List<ToDo> pendientes = new ArrayList<>();
        for (ToDo tarea : repositorio.obtenerTodasLasTareas()) {
            if (!tarea.isCompletado()) {
                pendientes.add(tarea);
            }
        }
        return pendientes;
    }

    private void comprobarFechasYAlertar() {
        LocalDate hoy = LocalDate.now();
        boolean hayVencidas = false;

        for (ToDo tarea : repositorio.obtenerTodasLasTareas()) {
            if (!tarea.isCompletado() && tarea.getFechaLimite().isBefore(hoy)) {
                hayVencidas = true;
                break;
            }
        }

        if (hayVencidas) {
            for (String email : repositorio.obtenerEmails()) {
                mailer.enviarCorreo(email, "¡Alerta! Tienes tareas pendientes con fecha límite pasada.");
            }
        }
    }
}
