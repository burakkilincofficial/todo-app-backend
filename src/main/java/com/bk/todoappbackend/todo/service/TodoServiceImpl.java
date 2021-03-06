package com.bk.todoappbackend.todo.service;

import com.bk.todoappbackend.todo.entity.Todo;
import com.bk.todoappbackend.todo.exception.TodoIsAlreadyCompleted;
import com.bk.todoappbackend.todo.exception.TodoIsAlreadyInCompleted;
import com.bk.todoappbackend.todo.exception.TodoNotFoundException;
import com.bk.todoappbackend.todo.mapper.TodoMapper;
import com.bk.todoappbackend.todo.model.CreateTodoRequest;
import com.bk.todoappbackend.todo.model.UpdateTodoRequest;
import com.bk.todoappbackend.todo.model.response.AllTodoResponse;
import com.bk.todoappbackend.todo.model.response.CreateTodoResponse;
import com.bk.todoappbackend.todo.model.response.TodoResponse;
import com.bk.todoappbackend.todo.model.response.UpdateTodoResponse;
import com.bk.todoappbackend.todo.repository.TodoRepository;
import com.bk.todoappbackend.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    public AllTodoResponse getAllTodos() {
        List<Todo> allTodos = todoRepository.findAll();
        List<TodoResponse> todoResponses = new ArrayList<>();
        allTodos.forEach(todo -> todoResponses.add(todoMapper.convertTodo2TodoResponse(todo)));
        return AllTodoResponse.builder()
                .data(todoResponses)
                .todoCount(todoResponses.size())
                .build();
    }

    @Override
    public Todo findById(Integer id) throws TodoNotFoundException {
        return checkTodoIfExist(id);
    }

    @Override
    public CreateTodoResponse createNewTodo(CreateTodoRequest createTodoRequest) throws UserNotFoundException {
        Todo todo = todoMapper.convertCreateTodoRequest2Todo(createTodoRequest);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.convertTodo2CreateTodoResponse(savedTodo);
    }

    @Override
    public UpdateTodoResponse updateTodo(UpdateTodoRequest updateTodoRequest, Integer id) throws TodoNotFoundException, UserNotFoundException {
        Todo todo1 = checkTodoIfExist(id);
        Todo todo = todoMapper.convertUpdateTodoRequest2Todo(updateTodoRequest, todo1);
        Todo updatedTodo = todoRepository.save(todo);
        return todoMapper.convertTodo2UpdateTodoResponse(updatedTodo);
    }

    @Override
    public UpdateTodoResponse completeTodo(Integer id) throws TodoNotFoundException, TodoIsAlreadyCompleted {
        Todo todo = findById(id);
        checkTodoIfAlreadyCompleted(todo);
        todo.setIsCompleted(true);
        todo.setCompletedDate(new Date());
        todoRepository.save(todo);
        return todoMapper.convertTodo2UpdateTodoResponse(todo);
    }

    @Override
    public UpdateTodoResponse inComplete(Integer id) throws TodoNotFoundException, TodoIsAlreadyInCompleted {
        Todo todo = findById(id);
        checkTodoIfAlreadyInCompleted(todo);
        todo.setIsCompleted(false);
        todo.setCompletedDate(null);
        todoRepository.save(todo);
        return todoMapper.convertTodo2UpdateTodoResponse(todo);
    }

    @Override
    public AllTodoResponse getAllTodosByUsername(String name) {
        List<Todo> allTodosByUserName = todoRepository.findAllByUser_UserName(name);
        List<TodoResponse> todoResponses = new ArrayList<>();
        allTodosByUserName.forEach(todo -> todoResponses.add(todoMapper.convertTodo2TodoResponse(todo)));
        return AllTodoResponse.builder()
                .data(todoResponses)
                .todoCount(todoResponses.size())
                .build();
    }

    @Override
    public void deleteById(Integer id) throws TodoNotFoundException {
        findById(id);
        todoRepository.deleteById(id);
    }


    public Todo checkTodoIfExist(Integer id) throws TodoNotFoundException {
        return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(String.format("todo not found, id: %s", id)));
    }

    public void checkTodoIfAlreadyCompleted(Todo todo) throws TodoIsAlreadyCompleted {
        if (todo.getIsCompleted()) {
            throw new TodoIsAlreadyCompleted(String.format("todo is already completed, id: %s", todo.getId()));
        }
    }

    private void checkTodoIfAlreadyInCompleted(Todo todo) throws TodoIsAlreadyInCompleted {
        if (!todo.getIsCompleted()) {
            throw new TodoIsAlreadyInCompleted(String.format("todo is already in-completed, id: %s", todo.getId()));
        }
    }

}
