package com.tt1.test;

import com.tt1.test.MailerStub;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Test_MailerStub {

    private MailerStub mailer;

    @BeforeEach
    void setUp() {
        mailer = new MailerStub();
    }

    @Test
    void testEnvioDevuelveTrue() {
        // Act
        boolean resultado = mailer.enviarEmail("test@email.com", "Mensaje de prueba");
        // Assert
        assertTrue(resultado);
    }

    @Test
    void testEnvioConDireccionVaciaDevuelveFalse() {
        boolean resultado = mailer.enviarEmail("", "Mensaje");
        assertFalse(resultado);
    }
}
