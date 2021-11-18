package com.bk.todoappbackend.todo.controller;

import com.bk.todoappbackend.todo.entity.Todo;
import com.bk.todoappbackend.todo.exception.TodoIsAlreadyCompleted;
import com.bk.todoappbackend.todo.exception.TodoIsAlreadyInCompleted;
import com.bk.todoappbackend.todo.exception.TodoNotFoundException;
import com.bk.todoappbackend.todo.model.CreateTodoRequest;
import com.bk.todoappbackend.todo.model.UpdateTodoRequest;
import com.bk.todoappbackend.todo.model.response.AllTodoResponse;
import com.bk.todoappbackend.todo.model.response.CreateTodoResponse;
import com.bk.todoappbackend.todo.model.response.UpdateTodoResponse;
import com.bk.todoappbackend.todo.service.TodoService;
import com.bk.todoappbackend.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/all")
    public AllTodoResponse getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping(value = "/user-name/{name}")
    public AllTodoResponse getAllTodos(@PathVariable("name") String name) {
        return todoService.getAllTodosByUsername(name);
    }

    @PostMapping("/{name}")
    public CreateTodoResponse createNewTodo(@PathVariable String name, @RequestBody CreateTodoRequest createTodoRequest) throws UserNotFoundException {
        createTodoRequest.setUserName(name);
        return todoService.createNewTodo(createTodoRequest);
    }

    @GetMapping(path = "/{id}")
    public Todo getTodo(@PathVariable("id") Integer id) throws TodoNotFoundException {
        return todoService.findById(id);
    }

    @PutMapping("/{id}")
    public UpdateTodoResponse updateTodo(@PathVariable Integer id, @RequestBody UpdateTodoRequest updateTodoRequest) throws TodoNotFoundException, UserNotFoundException {
        return todoService.updateTodo(updateTodoRequest, id);
    }

    @PostMapping(path = "/complete/{id}")
    public UpdateTodoResponse completeTodo(@PathVariable Integer id) throws TodoNotFoundException, TodoIsAlreadyCompleted {
        return todoService.completeTodo(id);
    }

    @PostMapping(path = "/incomplete/{id}")
    public UpdateTodoResponse inCompleteTodo(@PathVariable Integer id) throws TodoNotFoundException, TodoIsAlreadyInCompleted {
        return todoService.inComplete(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Integer id) throws TodoNotFoundException {
        todoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
