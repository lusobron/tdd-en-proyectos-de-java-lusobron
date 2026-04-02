package com.tt1.test;

import com.tt1.test.DBStub;
import com.tt1.test.Repositorio;
import com.tt1.test.ToDo;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

// Usa DBStub REAL — verifica que Repositorio y DBStub funcionan juntos
class Test_Repositorio_Integracion {

    private Repositorio repositorio;

    @BeforeEach
    void setUp() {
        repositorio = new Repositorio(new DBStub());
    }

    @Test
    void testGuardarYEncontrarTodo() {
        ToDo todo = new ToDo("Integración", "Desc", LocalDate.now());
        repositorio.guardarTodo(todo);
        assertEquals("Integración", repositorio.encontrarTodo("Integración").getNombre());
    }

    @Test
    void testFlujoCompletoGuardarMarcarYVerificar() {
        ToDo todo = new ToDo("Flujo", "Desc", LocalDate.now());
        repositorio.guardarTodo(todo);
        repositorio.marcarCompletada("Flujo");
        assertTrue(repositorio.encontrarTodo("Flujo").isCompletado());
    }

    @Test
    void testPendientesTrasMarcarAlgunas() {
        repositorio.guardarTodo(new ToDo("T1", "Desc", LocalDate.now()));
        repositorio.guardarTodo(new ToDo("T2", "Desc", LocalDate.now()));
        repositorio.marcarCompletada("T1");
        assertEquals(1, repositorio.encontrarPendientes().size());
    }
}
