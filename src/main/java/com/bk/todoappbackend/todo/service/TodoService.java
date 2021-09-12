package com.bk.todoappbackend.todo.service;

import com.bk.todoappbackend.todo.entity.Todo;
import com.bk.todoappbackend.todo.exception.TodoIsAlreadyCompleted;
import com.bk.todoappbackend.todo.exception.TodoIsAlreadyInCompleted;
import com.bk.todoappbackend.todo.exception.TodoNotFoundException;
import com.bk.todoappbackend.todo.model.CreateTodoRequest;
import com.bk.todoappbackend.todo.model.UpdateTodoRequest;
import com.bk.todoappbackend.todo.model.response.CreateTodoResponse;
import com.bk.todoappbackend.todo.model.response.UpdateTodoResponse;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();

    Todo findById(Integer id) throws TodoNotFoundException;

    CreateTodoResponse createNewTodo(CreateTodoRequest createTodoRequest);

    UpdateTodoResponse updateTodo(UpdateTodoRequest updateTodoRequest) throws TodoNotFoundException;

    UpdateTodoResponse completeTodo(Integer id) throws TodoNotFoundException, TodoIsAlreadyCompleted;

    UpdateTodoResponse inComplete(Integer id) throws TodoNotFoundException, TodoIsAlreadyInCompleted;
}
