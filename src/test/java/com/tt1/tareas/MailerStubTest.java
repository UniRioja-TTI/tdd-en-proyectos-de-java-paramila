package com.tt1.tareas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MailerStubTest {

    @Test
    void testEnviarCorreoDevuelveTrue() {
        MailerStub mailer = new MailerStub();

        boolean resultado = mailer.enviarCorreo("ana@correo.com", "Hola");

        assertTrue(resultado);
    }
}
