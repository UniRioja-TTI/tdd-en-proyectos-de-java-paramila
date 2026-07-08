package com.tt1.tareas.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.tt1.tareas.IRepositorio;
import com.tt1.tareas.ToDo;

public class RepositorioFake implements IRepositorio {

    private Map<String, ToDo> tareas = new HashMap<>();
    private HashSet<String> emails = new HashSet<>();

    @Override
    public ToDo encontrarToDo(String nombre) { return tareas.get(nombre); }

    @Override
    public boolean marcarCompletado(String nombre) {
        ToDo t = tareas.get(nombre);
        if (t != null) {
            t.setCompletado(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean almacenarToDo(ToDo todo) {
        tareas.put(todo.getNombre(), todo);
        return true;
    }

    @Override
    public boolean almacenarEmail(String email) {
        emails.add(email);
        return true;
    }

    @Override
    public List<ToDo> obtenerTodasLasTareas() { return new ArrayList<>(tareas.values()); }

    @Override
    public HashSet<String> obtenerEmails() { return emails; }
}
