package com.tt1.tareas.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.tt1.tareas.IBaseDatos;
import com.tt1.tareas.ToDo;

public class BaseDatosFake implements IBaseDatos {

    private Map<String, ToDo> tareas = new HashMap<>();
    private HashSet<String> emails = new HashSet<>();

    @Override
    public void guardarTarea(ToDo tarea) { tareas.put(tarea.getNombre(), tarea); }

    @Override
    public ToDo obtenerTarea(String nombre) { return tareas.get(nombre); }

    @Override
    public void actualizarTarea(ToDo tarea) { tareas.put(tarea.getNombre(), tarea); }

    @Override
    public void borrarTarea(String nombre) { tareas.remove(nombre); }

    @Override
    public List<ToDo> obtenerTodasLasTareas() { return new ArrayList<>(tareas.values()); }

    @Override
    public void guardarEmail(String email) { emails.add(email); }

    @Override
    public HashSet<String> obtenerEmails() { return emails; }
}
