package com.tt1.test;

import com.tt1.test.Repositorio;
import com.tt1.test.Servicio;
import com.tt1.test.ToDo;
import com.tt1.test.mock.DBStubFake;
import com.tt1.test.mock.MailerStubFake;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class Test_Servicio_Unitario {

    private DBStubFake dbFake;
    private MailerStubFake mailerFake;
    private Servicio servicio;

    @BeforeEach
    void setUp() {
        dbFake = new DBStubFake();
        mailerFake = new MailerStubFake();
        Repositorio repositorio = new Repositorio(dbFake);
        servicio = new Servicio(repositorio, mailerFake);
    }

    @Test
    void testCrearTodoSeAlmacena() {
        servicio.crearTodo("Tarea", LocalDate.of(2025, 12, 31));
        assertNotNull(dbFake.getTodo("Tarea"));
    }

    @Test
    void testAgregarEmailInvalidoNoSeAlmacena() {
        servicio.agregarEmail("esto-no-es-un-email");
        assertTrue(dbFake.getEmails().isEmpty());
    }

    @Test
    void testAgregarEmailValidoSeAlmacena() {
        servicio.agregarEmail("valido@email.com");
        assertTrue(dbFake.getEmails().contains("valido@email.com"));
    }

    @Test
    void testTareaVencidaDisparaEmail() {
        // Arrange: tarea con fecha pasada
        dbFake.addTodo(new ToDo("Vencida", "Desc", LocalDate.of(2000, 1, 1)));
        dbFake.addEmail("alerta@email.com");

        // Act: cualquier operación del servicio comprueba vencidos
        servicio.listarPendientes();

        // Assert: el mailer fake registró el envío
        assertTrue(mailerFake.destinatariosContactados.contains("alerta@email.com"));
    }

    @Test
    void testNombreVacioNoCreaLaTarea() {
        servicio.crearTodo("", LocalDate.of(2025, 12, 31));
        assertNull(dbFake.getTodo(""));
    }
}
