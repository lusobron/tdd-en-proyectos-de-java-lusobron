package com.tt1.test;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Capa de servicio que expone las operaciones disponibles para el usuario.
 * Coordina el {@link Repositorio} y el {@link IMailerStub} para implementar
 * los casos de uso principales: crear tareas, gestionar la agenda de emails,
 * marcar tareas como completadas y listar pendientes.
 *
 * <p>Tras cada operación, comprueba automáticamente si existen tareas
 * vencidas y envía alertas a todos los emails de la agenda.</p>
 *
 * @author Luis Sobrón Troya
 * @version 1.0
 */
public class Servicio {
    private Repositorio repositorio;
    private IMailerStub mailer;

    /**
     * Crea el servicio con las dependencias necesarias.
     *
     * @param repositorio Repositorio de acceso a datos.
     * @param mailer      Implementación del servicio de envío de emails.
     */
    public Servicio(Repositorio repositorio, IMailerStub mailer) {
        this.repositorio = repositorio;
        this.mailer = mailer;
    }

    /**
     * Crea y almacena una nueva tarea con el nombre y fecha límite indicados.
     * No realiza ninguna acción si el nombre es nulo o vacío.
     * Tras crear la tarea, comprueba si hay tareas vencidas y notifica.
     *
     * @param nombre      Nombre identificador de la tarea. No debe ser vacío.
     * @param fechaLimite Fecha límite para completar la tarea.
     */
    public void crearTodo(String nombre, LocalDate fechaLimite) {
        if (nombre == null || nombre.trim().isEmpty()) return;
        ToDo todo = new ToDo(nombre, "", fechaLimite);
        repositorio.guardarTodo(todo);
        comprobarVencidosYNotificar();
    }

    /**
     * Añade una dirección de email a la agenda de contactos.
     * Valida que el email tenga un formato básico correcto antes de almacenarlo.
     * Tras añadir el email, comprueba si hay tareas vencidas y notifica.
     *
     * @param email Dirección de email a añadir. Debe contener '@' y '.'.
     */
    public void agregarEmail(String email) {
        if (!emailValido(email)) return;
        repositorio.guardarEmail(email);
        comprobarVencidosYNotificar();
    }

    /**
     * Marca una tarea existente como completada.
     * Tras la operación, comprueba si hay tareas vencidas y notifica.
     *
     * @param nombre Nombre de la tarea a marcar como completada.
     */
    public void marcarCompletada(String nombre) {
        repositorio.marcarCompletada(nombre);
        comprobarVencidosYNotificar();
    }

    /**
     * Imprime por consola todas las tareas pendientes de completar,
     * mostrando su nombre y fecha límite.
     * Tras listar, comprueba si hay tareas vencidas y notifica.
     */
    public void listarPendientes() {
        Collection<ToDo> pendientes = repositorio.encontrarPendientes();
        pendientes.forEach(t -> System.out.println(
            t.getNombre() + " — límite: " + t.getFechaLimite()
        ));
        comprobarVencidosYNotificar();
    }

    /**
     * Comprueba si hay tareas pendientes cuya fecha límite ya ha pasado.
     * Si las hay, envía un email de alerta a todas las direcciones de la agenda.
     */
    private void comprobarVencidosYNotificar() {
        LocalDate hoy = LocalDate.now();
        Collection<ToDo> pendientes = repositorio.encontrarPendientes();
        boolean hayVencidos = pendientes.stream()
                .anyMatch(t -> t.getFechaLimite().isBefore(hoy));

        if (hayVencidos) {
            repositorio.obtenerEmails().forEach(email ->
                mailer.enviarEmail(email, "Hay tareas pendientes con fecha límite superada.")
            );
        }
    }

    /**
     * Valida que una dirección de email tenga un formato básico correcto.
     *
     * @param email Dirección a validar.
     * @return {@code true} si el email contiene '@' y '.', {@code false} en caso contrario.
     */
    private boolean emailValido(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}
