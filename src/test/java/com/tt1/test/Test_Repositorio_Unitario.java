package com.tt1.test;

import com.tt1.test.Repositorio;
import com.tt1.test.ToDo;
import com.tt1.test.mock.DBStubFake;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class Test_Repositorio_Unitario {

    // Usamos el fake, no el DBStub real — test unitario puro
    private DBStubFake dbFake;
    private Repositorio repositorio;
    private ToDo todo;

    @BeforeEach
    void setUp() {
        dbFake = new DBStubFake();
        repositorio = new Repositorio(dbFake);
        todo = new ToDo("Tarea 1", "Desc", LocalDate.of(2025, 12, 31));
    }

    @Test
    void testGuardarTodoLoAlmacenaEnDb() {
        repositorio.guardarTodo(todo);
        assertNotNull(dbFake.getTodo("Tarea 1"));
    }

    @Test
    void testEncontrarTodoDevuelveElCorrecto() {
        dbFake.addTodo(todo);
        ToDo encontrado = repositorio.encontrarTodo("Tarea 1");
        assertEquals("Tarea 1", encontrado.getNombre());
    }

    @Test
    void testMarcarCompletadaCambiaEstado() {
        dbFake.addTodo(todo);
        repositorio.marcarCompletada("Tarea 1");
        assertTrue(dbFake.getTodo("Tarea 1").isCompletado());
    }

    @Test
    void testEncontrarPendientesSoloDevuelveLasNoCompletadas() {
        ToDo completada = new ToDo("Completada", "Desc", LocalDate.now());
        completada.setCompletado(true);
        dbFake.addTodo(todo);
        dbFake.addTodo(completada);

        long pendientes = repositorio.encontrarPendientes().size();
        assertEquals(1, pendientes);
    }
}
