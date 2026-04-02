package com.tt1.test;

import java.util.*;

/**
 * Implementación en memoria de {@link IDBStub} que simula una base de datos.
 * Almacena las tareas en un {@link HashMap} indexado por nombre,
 * garantizando acceso eficiente por identificador.
 * La agenda de emails se almacena en una {@link ArrayList}.
 *
 * <p>Esta clase actúa como stub de base de datos: su propósito es
 * permitir el desarrollo y testing sin depender de una base de datos real.</p>
 *
 * @author Luis Sobrón Troya
 * @version 1.0
 */
public class DBStub implements IDBStub {
    private Map<String, ToDo> tareas = new HashMap<>();
    private List<String> emails = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTodo(ToDo todo) {
        tareas.put(todo.getNombre(), todo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ToDo getTodo(String nombre) {
        return tareas.get(nombre);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<ToDo> getTodos() {
        return tareas.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTodo(ToDo todo) {
        tareas.put(todo.getNombre(), todo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTodo(String nombre) {
        tareas.remove(nombre);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEmail(String email) {
        emails.add(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getEmails() {
        return emails;
    }
}