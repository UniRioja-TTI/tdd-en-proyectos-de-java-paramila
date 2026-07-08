package com.tt1.tareas.mock;

import java.util.ArrayList;
import java.util.List;

import com.tt1.tareas.IMailer;

public class MailerMock implements IMailer {

    public int vecesLlamado = 0;
    public List<String> destinatarios = new ArrayList<>();
    public String ultimoMensaje = null;

    @Override
    public boolean enviarCorreo(String direccion, String mensaje) {
        vecesLlamado++;
        destinatarios.add(direccion);
        ultimoMensaje = mensaje;
        return true;
    }
}
