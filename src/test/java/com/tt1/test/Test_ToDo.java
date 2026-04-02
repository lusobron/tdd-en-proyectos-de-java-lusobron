package com.tt1.test;

import com.tt1.test.ToDo;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class Test_ToDo {

    private ToDo todo;

    // Arrange: se ejecuta antes de cada test con un ToDo limpio
    @BeforeEach
    void setUp() {
        todo = new ToDo("Tarea 1", "Descripción", LocalDate.of(2025, 12, 31));
    }

    @Test
    void testNombreSeAlmacenaCorrectamente() {
        // Assert directo sobre el arrange
        assertEquals("Tarea 1", todo.getNombre());
    }

    @Test
    void testCompletadoEsFalsePorDefecto() {
        assertFalse(todo.isCompletado());
    }

    @Test
    void testSetCompletadoCambiaEstado() {
        // Act
        todo.setCompletado(true);
        // Assert
        assertTrue(todo.isCompletado());
    }

    @Test
    void testFechaLimiteSeAlmacenaCorrectamente() {
        assertEquals(LocalDate.of(2025, 12, 31), todo.getFechaLimite());
    }

    @Test
    void testSetDescripcionFunciona() {
        todo.setDescripcion("Nueva descripción");
        assertEquals("Nueva descripción", todo.getDescripcion());
    }
}