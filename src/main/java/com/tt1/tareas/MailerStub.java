package com.tt1.tareas;

/**
 * Simula un servicio de envío de correos electrónicos (Mailer).
 * En lugar de enviar un email real, imprime un mensaje por la consola estándar.
 */
public class MailerStub {

    /**
     * Simula el envío de un correo electrónico a una dirección específica.
     * * @param direccion La dirección de correo del destinatario.
     * @param mensaje El contenido o cuerpo del mensaje a enviar.
     * @return true simulando que el correo siempre se envía con éxito.
     */
    public boolean enviarCorreo(String direccion, String mensaje) {
        System.out.println("Enviando EMAIL a [" + direccion + "]: " + mensaje);
        return true; // Confirmación de éxito
    }
}