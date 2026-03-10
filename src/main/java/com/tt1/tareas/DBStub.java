package com.tt1.tareas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Simula el comportamiento de una base de datos real (Stub) guardando los datos en memoria.
 * Utiliza estructuras de datos como HashMap y HashSet para almacenar tareas y correos.
 */
public class DBStub {
    private HashMap<String, ToDo> tareas = new HashMap<>();
    private HashSet<String> emails = new HashSet<>();

    /**
     * Inserta una nueva tarea en el almacenamiento en memoria.
     * * @param tarea El objeto ToDo a guardar, usando su nombre como clave.
     */
    public void guardarTarea(ToDo tarea) { tareas.put(tarea.getNombre(), tarea); }

    /**
     * Recupera una tarea previamente guardada.
     * * @param nombre El nombre exacto de la tarea a recuperar.
     * @return La tarea solicitada o null si no se encuentra.
     */
    public ToDo obtenerTarea(String nombre) { return tareas.get(nombre); }

    /**
     * Sobrescribe una tarea existente con sus nuevos valores.
     * * @param tarea El objeto ToDo actualizado.
     */
    public void actualizarTarea(ToDo tarea) { tareas.put(tarea.getNombre(), tarea); }

    /**
     * Elimina una tarea del almacenamiento.
     * * @param nombre El nombre de la tarea que se va a borrar.
     */
    public void borrarTarea(String nombre) { tareas.remove(nombre); }

    /**
     * Devuelve una lista con todos los valores de las tareas guardadas en el HashMap.
     * * @return Una lista completa de todas las tareas.
     */
    public List<ToDo> obtenerTodasLasTareas() { return new ArrayList<>(tareas.values()); }

    /**
     * Añade un nuevo correo electrónico a la lista de destinatarios.
     * * @param email La dirección de correo a añadir.
     */
    public void guardarEmail(String email) { emails.add(email); }

    /**
     * Devuelve todos los correos registrados para el sistema de alertas.
     * * @return Un HashSet con todos los correos electrónicos.
     */
    public HashSet<String> obtenerEmails() { return emails; }
}