package com.tt1.tareas;

import java.util.HashSet;
import java.util.List;

/**
 * Actúa como intermediario entre la lógica de negocio (Servicio) y la base de datos (DBStub).
 * Proporciona métodos para almacenar, buscar y actualizar tareas y correos electrónicos.
 */
public class Repositorio {
    private DBStub db;

    /**
     * Construye un nuevo Repositorio inyectando la dependencia de la base de datos.
     * * @param db La instancia de la base de datos simulada (DBStub) que se utilizará.
     */
    public Repositorio(DBStub db) {
        this.db = db;
    }

    /**
     * Busca una tarea en la base de datos por su nombre.
     * * @param nombre El identificador o nombre de la tarea a buscar.
     * @return El objeto ToDo encontrado, o null si no existe.
     */
    public ToDo encontrarToDo(String nombre) {
        return db.obtenerTarea(nombre);
    }

    /**
     * Marca una tarea existente como completada y actualiza la base de datos.
     * * @param nombre El nombre de la tarea que se desea completar.
     * @return true si la tarea se encontró y actualizó con éxito, false si no existe.
     */
    public boolean marcarCompletado(String nombre) {
        ToDo tarea = db.obtenerTarea(nombre);
        if (tarea != null) {
            tarea.setCompletado(true);
            db.actualizarTarea(tarea);
            return true;
        }
        return false;
    }

    /**
     * Guarda una nueva tarea en la base de datos.
     * * @param todo El objeto ToDo que se desea almacenar.
     * @return true si la operación de guardado fue exitosa.
     */
    public boolean almacenarToDo(ToDo todo) {
        db.guardarTarea(todo);
        return true;
    }

    /**
     * Guarda un nuevo correo electrónico en la base de datos para notificaciones.
     * * @param email La dirección de correo electrónico a almacenar.
     * @return true si el correo se guardó correctamente.
     */
    public boolean almacenarEmail(String email) {
        db.guardarEmail(email);
        return true;
    }

    /**
     * Obtiene una lista con todas las tareas almacenadas actualmente.
     * * @return Una lista (List) de objetos ToDo.
     */
    public List<ToDo> obtenerTodasLasTareas() { return db.obtenerTodasLasTareas(); }

    /**
     * Obtiene una colección con todos los correos electrónicos registrados.
     * * @return Un conjunto (HashSet) de cadenas de texto con los emails.
     */
    public HashSet<String> obtenerEmails() { return db.obtenerEmails(); }
}