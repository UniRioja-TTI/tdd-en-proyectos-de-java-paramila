package com.tt1.tareas;

import java.util.HashSet;
import java.util.List;


public interface IRepositorio {

    ToDo encontrarToDo(String nombre);

    boolean marcarCompletado(String nombre);

    boolean almacenarToDo(ToDo todo);

    boolean almacenarEmail(String email);

    List<ToDo> obtenerTodasLasTareas();

    HashSet<String> obtenerEmails();
}
