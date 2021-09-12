package com.bk.todoappbackend.todo.controller;

import com.bk.todoappbackend.todo.entity.Todo;
import com.bk.todoappbackend.todo.exception.TodoIsAlreadyCompleted;
import com.bk.todoappbackend.todo.exception.TodoIsAlreadyInCompleted;
import com.bk.todoappbackend.todo.exception.TodoNotFoundException;
import com.bk.todoappbackend.todo.model.CreateTodoRequest;
import com.bk.todoappbackend.todo.model.UpdateTodoRequest;
import com.bk.todoappbackend.todo.model.response.CreateTodoResponse;
import com.bk.todoappbackend.todo.model.response.UpdateTodoResponse;
import com.bk.todoappbackend.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(path = "/")
    public AllTodoResponse getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping(path = "/")
    public CreateTodoResponse createNewTodo(@RequestBody CreateTodoRequest createTodoRequest) {
        return todoService.createNewTodo(createTodoRequest);
    }

    @GetMapping(path = "/{id}")
    public Todo getTodo(@PathVariable Integer id) throws TodoNotFoundException {
        return todoService.findById(id);
    }

    @PutMapping(path = "/")
    public UpdateTodoResponse updateTodo(@RequestBody UpdateTodoRequest updateTodoRequest) throws TodoNotFoundException {
        return todoService.updateTodo(updateTodoRequest);
    }

    @PostMapping(path = "/complete/{id}")
    public UpdateTodoResponse completeTodo(@PathVariable Integer id) throws TodoNotFoundException, TodoIsAlreadyCompleted {
        return todoService.completeTodo(id);
    }

    @PostMapping(path = "/incomplete/{id}")
    public UpdateTodoResponse inCompleteTodo(@PathVariable Integer id) throws TodoNotFoundException, TodoIsAlreadyInCompleted {
        return todoService.inComplete(id);
    }
}
