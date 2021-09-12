package com.bk.todoappbackend.todo.service;

import com.bk.todoappbackend.todo.entity.Todo;
import com.bk.todoappbackend.todo.exception.TodoIsAlreadyCompleted;
import com.bk.todoappbackend.todo.exception.TodoIsAlreadyInCompleted;
import com.bk.todoappbackend.todo.exception.TodoNotFoundException;
import com.bk.todoappbackend.todo.mapper.TodoMapper;
import com.bk.todoappbackend.todo.model.CreateTodoRequest;
import com.bk.todoappbackend.todo.model.UpdateTodoRequest;
import com.bk.todoappbackend.todo.model.response.CreateTodoResponse;
import com.bk.todoappbackend.todo.model.response.UpdateTodoResponse;
import com.bk.todoappbackend.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoServiceImpl(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo findById(Integer id) throws TodoNotFoundException {
        return checkTodoIfExist(id);
    }

    @Override
    public CreateTodoResponse createNewTodo(CreateTodoRequest createTodoRequest) {
        Todo todo = todoMapper.convertCreateTodoRequest2Todo(createTodoRequest);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.convertTodo2CreateTodoResponse(savedTodo);
    }

    @Override
    public UpdateTodoResponse updateTodo(UpdateTodoRequest updateTodoRequest) throws TodoNotFoundException {
        checkTodoIfExist(updateTodoRequest.getId());
        Todo todo = todoMapper.convertUpdateTodoRequest2Todo(updateTodoRequest);
        Todo updatedTodo = todoRepository.save(todo);
        return todoMapper.convertTodo2UpdateTodoResponse(updatedTodo);
    }

    @Override
    public UpdateTodoResponse completeTodo(Integer id) throws TodoNotFoundException, TodoIsAlreadyCompleted {
        Todo todo = findById(id);
        checkTodoIfAlreadyCompleted(todo);
        todo.setCompleted(true);
        todoRepository.save(todo);
        return todoMapper.convertTodo2UpdateTodoResponse(todo);
    }

    @Override
    public UpdateTodoResponse inComplete(Integer id) throws TodoNotFoundException, TodoIsAlreadyInCompleted {
        Todo todo = findById(id);
        checkTodoIfAlreadyInCompleted(todo);
        todo.setCompleted(false);
        todoRepository.save(todo);
        return todoMapper.convertTodo2UpdateTodoResponse(todo);
    }


    public Todo checkTodoIfExist(Integer id) throws TodoNotFoundException {
        return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(String.format("todo not found, id: %s", id)));
    }

    public void checkTodoIfAlreadyCompleted(Todo todo) throws TodoIsAlreadyCompleted {
        if (todo.isCompleted()) {
            throw new TodoIsAlreadyCompleted(String.format("todo is already completed, id: %s", todo.getId()));
        }
    }

    private void checkTodoIfAlreadyInCompleted(Todo todo) throws TodoIsAlreadyInCompleted {
        if(!todo.isCompleted()){
            throw new TodoIsAlreadyInCompleted(String.format("todo is already in-completed, id: %s", todo.getId()));
        }
    }

}