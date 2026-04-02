package com.tt1.test;

import java.time.LocalDate;

/**
 * Representa una tarea pendiente en el sistema.
 * Es una JavaBean que almacena el nombre, descripción,
 * fecha límite y estado de compleción de una tarea.
 *
 * @author Luis Sobrón Troya
 * @version 1.0
 */
public class ToDo {
    private String nombre;
    private String descripcion;
    private LocalDate fechaLimite;
    private boolean completado;
    
    /**
     * Constructor sin argumentos requerido por la especificación JavaBean.
     */
    public ToDo() {}

    /**
     * Crea una nueva tarea con los datos proporcionados.
     * El estado de compleción se inicializa a {@code false}.
     *
     * @param nombre      Nombre identificador de la tarea. Debe ser único y no vacío.
     * @param descripcion Descripción detallada del contenido de la tarea.
     * @param fechaLimite Fecha límite para completar la tarea.
     */
    public ToDo(String nombre, String descripcion, LocalDate fechaLimite) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.completado = false;
    }
    
    /**
     * Devuelve el nombre de la tarea.
     *
     * @return Nombre de la tarea.
     */
    public String getNombre() { return nombre; }
    
    /**
     * Establece el nombre de la tarea.
     *
     * @param nombre Nuevo nombre de la tarea.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Devuelve la descripción de la tarea.
     *
     * @return Descripción de la tarea.
     */
    public String getDescripcion() { return descripcion; }

    /**
     * Establece la descripción de la tarea.
     *
     * @param descripcion Nueva descripción de la tarea.
     */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    /**
     * Devuelve la fecha límite de la tarea.
     *
     * @return Fecha límite como {@link LocalDate}.
     */
    public LocalDate getFechaLimite() { return fechaLimite; }

    /**
     * Establece la fecha límite de la tarea.
     *
     * @param fechaLimite Nueva fecha límite.
     */
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }

    /**
     * Indica si la tarea ha sido completada.
     *
     * @return {@code true} si la tarea está completada, {@code false} en caso contrario.
     */
    public boolean isCompletado() { return completado; }

    /**
     * Establece el estado de compleción de la tarea.
     *
     * @param completado {@code true} para marcar la tarea como completada.
     */
    public void setCompletado(boolean completado) { this.completado = completado; }
}
