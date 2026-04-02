package com.tt1.test;

/**
 * Interfaz que define el contrato para el envío de correos electrónicos.
 * Permite desacoplar el servicio de la implementación concreta del mailer,
 * facilitando el uso de implementaciones simuladas en tests.
 *
 * @author Luis Sobrón Troya
 * @version 1.0
 */
public interface IMailerStub {

    /**
     * Envía un correo electrónico a la dirección indicada.
     *
     * @param direccion Dirección de email del destinatario.
     * @param mensaje   Contenido del mensaje a enviar.
     * @return {@code true} si el envío fue exitoso, {@code false} en caso contrario.
     */
    boolean enviarEmail(String direccion, String mensaje);
}