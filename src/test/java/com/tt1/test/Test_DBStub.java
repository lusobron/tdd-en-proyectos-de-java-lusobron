package com.tt1.test;

import com.tt1.test.DBStub;
import com.tt1.test.ToDo;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class Test_DBStub {

    private DBStub db;
    private ToDo todo;

    @BeforeEach
    void setUp() {
        db = new DBStub();
        todo = new ToDo("Tarea 1", "Desc", LocalDate.of(2025, 12, 31));
    }

    @Test
    void testAddYGetTodo() {
        // Act
        db.addTodo(todo);
        // Assert
        assertEquals(todo, db.getTodo("Tarea 1"));
    }

    @Test
    void testGetTodoInexistenteDevuelveNull() {
        assertNull(db.getTodo("NoExiste"));
    }

    @Test
    void testDeleteTodo() {
        db.addTodo(todo);
        db.deleteTodo("Tarea 1");
        assertNull(db.getTodo("Tarea 1"));
    }

    @Test
    void testUpdateTodo() {
        db.addTodo(todo);
        todo.setCompletado(true);
        db.updateTodo(todo);
        assertTrue(db.getTodo("Tarea 1").isCompletado());
    }

    @Test
    void testAddEmail() {
        db.addEmail("user@email.com");
        assertTrue(db.getEmails().contains("user@email.com"));
    }

    @Test
    void testGetTodosDevuelveTodasLasTareas() {
        ToDo todo2 = new ToDo("Tarea 2", "Desc", LocalDate.now());
        db.addTodo(todo);
        db.addTodo(todo2);
        assertEquals(2, db.getTodos().size());
    }
}
