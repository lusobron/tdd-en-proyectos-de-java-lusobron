package com.tt1.test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Capa de acceso a datos que actúa como intermediaria entre
 * el {@link Servicio} y el almacenamiento ({@link IDBStub}).
 * Encapsula las operaciones de persistencia y filtra los datos
 * según las necesidades de la lógica de negocio.
 *
 * @author TT1
 * @version 1.0
 */
public class Repositorio {
    private IDBStub db;

    /**
     * Crea un repositorio con la implementación de base de datos indicada.
     * Permite inyectar tanto la implementación real como un stub para testing.
     *
     * @param db Implementación de {@link IDBStub} a utilizar.
     */
    public Repositorio(IDBStub db) {
        this.db = db;
    }

    /**
     * Persiste una nueva tarea en el almacenamiento.
     *
     * @param todo Tarea a almacenar.
     */
    public void guardarTodo(ToDo todo) {
        db.addTodo(todo);
    }

    /**
     * Busca una tarea por su nombre.
     *
     * @param nombre Nombre de la tarea a buscar.
     * @return La tarea encontrada, o {@code null} si no existe.
     */
    public ToDo encontrarTodo(String nombre) {
        return db.getTodo(nombre);
    }

    /**
     * Marca una tarea existente como completada.
     * Si la tarea no existe, no realiza ninguna acción.
     *
     * @param nombre Nombre de la tarea a marcar como completada.
     */
    public void marcarCompletada(String nombre) {
        ToDo todo = db.getTodo(nombre);
        if (todo != null) {
            todo.setCompletado(true);
            db.updateTodo(todo);
        }
    }

    /**
     * Devuelve todas las tareas que aún no han sido completadas.
     *
     * @return Colección de tareas pendientes.
     */
    public Collection<ToDo> encontrarPendientes() {
        return db.getTodos().stream()
                .filter(t -> !t.isCompletado())
                .collect(Collectors.toList());
    }

    /**
     * Almacena una dirección de email en la agenda.
     *
     * @param email Dirección de email a guardar.
     */
    public void guardarEmail(String email) {
        db.addEmail(email);
    }

    /**
     * Devuelve todas las direcciones de email de la agenda.
     *
     * @return Lista de direcciones de email.
     */
    public List<String> obtenerEmails() {
        return db.getEmails();
    }
}
