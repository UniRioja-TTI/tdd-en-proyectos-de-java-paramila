package com.tt1.tareas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que actúa como servicio de lógica de negocio del sistema de tareas.
 * Coordina las operaciones entre el repositorio de datos y el sistema de notificaciones por correo.
 */
public class Servicio {
    private Repositorio repositorio;
    private MailerStub mailer;

    /**
     * Construye el servicio inyectando sus dependencias principales.
     * * @param repositorio El gestor de almacenamiento de datos.
     * @param mailer El servicio encargado de enviar correos electrónicos.
     */
    public Servicio(Repositorio repositorio, MailerStub mailer) {
        this.repositorio = repositorio;
        this.mailer = mailer;
    }

    /**
     * Crea una nueva tarea y la almacena en el repositorio.
     * Tras añadirla, realiza una comprobación de fechas vencidas para enviar alertas si es necesario.
     * * @param nombre El título de la nueva tarea. No puede estar vacío.
     * @param fechaLimite La fecha máxima para resolverla.
     * @return true si la tarea se creó y guardó con éxito, false si el nombre es nulo o está vacío.
     */
    public boolean crearToDo(String nombre, LocalDate fechaLimite) {
        if (nombre == null || nombre.trim().isEmpty()) return false; // Validación
        ToDo nuevaTarea = new ToDo(nombre, "Sin descripción", fechaLimite);
        repositorio.almacenarToDo(nuevaTarea);
        comprobarFechasYAlertar(); // Comprueba tras añadir
        return true;
    }

    /**
     * Agrega un nuevo correo electrónico a la lista de alertas.
     * * @param email La dirección de correo a registrar.
     * @return true si se agregó correctamente, false si el formato parece inválido (no contiene '@').
     */
    public boolean agregarEmail(String email) {
        if (email == null || !email.contains("@")) return false; // Validación
        repositorio.almacenarEmail(email);
        comprobarFechasYAlertar(); // Comprueba tras añadir
        return true;
    }

    /**
     * Marca una tarea existente como finalizada.
     * * @param nombre El identificador de la tarea a completar.
     * @return true si la operación fue exitosa, false si la tarea no existía.
     */
    public boolean marcarFinalizada(String nombre) {
        boolean exito = repositorio.marcarCompletado(nombre);
        comprobarFechasYAlertar(); // Comprueba tras consultar
        return exito;
    }

    /**
     * Obtiene una lista de todas las tareas que aún no han sido completadas.
     * * @return Una lista (List) de objetos ToDo cuyo estado completado es false.
     */
    public List<ToDo> consultarPendientes() {
        comprobarFechasYAlertar(); // Comprueba tras consultar
        List<ToDo> pendientes = new ArrayList<>();
        for (ToDo tarea : repositorio.obtenerTodasLasTareas()) {
            if (!tarea.isCompletado()) {
                pendientes.add(tarea);
            }
        }
        return pendientes;
    }

    /**
     * Método interno que revisa todas las tareas pendientes.
     * Si encuentra alguna cuya fecha límite haya pasado, envía un correo de alerta a todos los usuarios.
     */
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