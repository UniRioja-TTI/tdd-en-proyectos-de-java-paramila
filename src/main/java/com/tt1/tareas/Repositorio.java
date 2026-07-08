package com.tt1.tareas;

import java.util.HashSet;
import java.util.List;

public class Repositorio implements IRepositorio {
    private IBaseDatos db;

    public Repositorio(IBaseDatos db) {
        this.db = db;
    }

    @Override
    public ToDo encontrarToDo(String nombre) {
        return db.obtenerTarea(nombre);
    }

    @Override
    public boolean marcarCompletado(String nombre) {
        ToDo tarea = db.obtenerTarea(nombre);
        if (tarea != null) {
            tarea.setCompletado(true);
            db.actualizarTarea(tarea);
            return true;
        }
        return false;
    }

    @Override
    public boolean almacenarToDo(ToDo todo) {
        db.guardarTarea(todo);
        return true;
    }

    @Override
    public boolean almacenarEmail(String email) {
        db.guardarEmail(email);
        return true;
    }

    @Override
    public List<ToDo> obtenerTodasLasTareas() { return db.obtenerTodasLasTareas(); }

    @Override
    public HashSet<String> obtenerEmails() { return db.obtenerEmails(); }
}
