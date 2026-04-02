package com.tt1.test.mock;

import com.tt1.test.IDBStub;
import com.tt1.test.ToDo;

import java.util.*;

public class DBStubFake implements IDBStub {
    private Map<String, ToDo> tareas = new HashMap<>();
    private List<String> emails = new ArrayList<>();

    @Override
    public void addTodo(ToDo todo) {
        tareas.put(todo.getNombre(), todo);
    }

    @Override
    public ToDo getTodo(String nombre) {
        return tareas.get(nombre);
    }

    @Override
    public Collection<ToDo> getTodos() {
        return tareas.values();
    }

    @Override
    public void updateTodo(ToDo todo) {
        tareas.put(todo.getNombre(), todo);
    }

    @Override
    public void deleteTodo(String nombre) {
        tareas.remove(nombre);
    }

    @Override
    public void addEmail(String email) {
        emails.add(email);
    }

    @Override
    public List<String> getEmails() {
        return emails;
    }
}
