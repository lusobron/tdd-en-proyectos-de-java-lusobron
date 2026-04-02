package com.tt1.test;

/**
 * Implementación simulada del servicio de envío de emails.
 * En lugar de enviar correos reales, imprime el destinatario
 * y el mensaje por consola. Útil durante el desarrollo para
 * verificar que el sistema de notificaciones funciona correctamente
 * sin depender de infraestructura de correo real.
 *
 * @author Luis Sobrón Troya
 * @version 1.0
 */
public class MailerStub implements IMailerStub {

	/**
     * Simula el envío de un email imprimiendo su contenido por consola.
     *
     * @param direccion Dirección de email del destinatario. No debe ser nula ni vacía.
     * @param mensaje   Contenido del mensaje a enviar.
     * @return {@code true} si el envío fue exitoso, {@code false} si la dirección es inválida.
     */
    @Override
    public boolean enviarEmail(String direccion, String mensaje) {
        if (direccion == null || direccion.isEmpty()) return false;
        System.out.println("Para: " + direccion);
        System.out.println("Mensaje: " + mensaje);
        return true;
    }
}