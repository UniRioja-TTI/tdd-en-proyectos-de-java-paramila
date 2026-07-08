package com.tt1.tareas;

import java.util.HashSet;
import java.util.List;


public interface IBaseDatos {

    void guardarTarea(ToDo tarea);

    ToDo obtenerTarea(String nombre);

    void actualizarTarea(ToDo tarea);

    void borrarTarea(String nombre);

    List<ToDo> obtenerTodasLasTareas();

    void guardarEmail(String email);


    HashSet<String> obtenerEmails();
}
