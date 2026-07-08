package com.tt1.tareas;

public class MailerStub implements IMailer {

    @Override
    public boolean enviarCorreo(String direccion, String mensaje) {
        System.out.println("Enviando EMAIL a [" + direccion + "]: " + mensaje);
        return true;
    }
}
