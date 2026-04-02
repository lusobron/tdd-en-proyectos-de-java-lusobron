package com.tt1.test.mock;

import com.tt1.test.IMailerStub;
import java.util.ArrayList;
import java.util.List;

public class MailerStubFake implements IMailerStub {
    // Registra los envíos para poder hacer asserts sobre ellos
    public List<String> destinatariosContactados = new ArrayList<>();
    public boolean debeSimularFallo = false;

    @Override
    public boolean enviarEmail(String direccion, String mensaje) {
        if (debeSimularFallo) return false;
        destinatariosContactados.add(direccion);
        return true;
    }
}
