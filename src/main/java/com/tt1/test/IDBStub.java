package com.tt1.test;

import java.util.Collection;
import java.util.List;

/**
 * Interfaz que define las operaciones de persistencia disponibles
 * sobre la colección de tareas y la agenda de emails.
 * Permite desacoplar la capa de repositorio de la implementación
 * concreta de almacenamiento, facilitando el uso de stubs o mocks en tests.
 *
 * @author Luis Sobrón Troya
 * @version 1.0
 */
public interface IDBStub {
	/**
     * Almacena una nueva tarea. Si ya existe una tarea con el mismo nombre,
     * la sobreescribe.
     *
     * @param todo Tarea a almacenar.
     */
    void addTodo(ToDo todo);

    /**
     * Recupera una tarea por su nombre.
     *
     * @param nombre Nombre de la tarea a buscar.
     * @return La tarea correspondiente, o {@code null} si no existe.
     */
    ToDo getTodo(String nombre);

    /**
     * Devuelve todas las tareas almacenadas.
     *
     * @return Colección con todas las tareas.
     */
    Collection<ToDo> getTodos();

    /**
     * Actualiza una tarea existente. La tarea se identifica por su nombre.
     *
     * @param todo Tarea con los datos actualizados.
     */
    void updateTodo(ToDo todo);

    /**
     * Elimina una tarea por su nombre.
     *
     * @param nombre Nombre de la tarea a eliminar.
     */
    void deleteTodo(String nombre);

    /**
     * Añade una dirección de email a la agenda.
     *
     * @param email Dirección de email a almacenar.
     */
    void addEmail(String email);

    /**
     * Devuelve todas las direcciones de email almacenadas en la agenda.
     *
     * @return Lista de direcciones de email.
     */
    List<String> getEmails();
}