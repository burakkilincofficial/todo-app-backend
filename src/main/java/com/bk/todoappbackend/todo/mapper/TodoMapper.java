package com.bk.todoappbackend.todo.mapper;

import com.bk.todoappbackend.todo.entity.Todo;
import com.bk.todoappbackend.todo.model.CreateTodoRequest;
import com.bk.todoappbackend.todo.model.UpdateTodoRequest;
import com.bk.todoappbackend.todo.model.response.CreateTodoResponse;
import com.bk.todoappbackend.todo.model.response.TodoResponse;
import com.bk.todoappbackend.todo.model.response.UpdateTodoResponse;
import com.bk.todoappbackend.user.entity.User;
import com.bk.todoappbackend.user.exception.UserNotFoundException;
import com.bk.todoappbackend.user.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TodoMapper {
    private final UserService userService;

    public TodoMapper(UserService userService) {
        this.userService = userService;
    }

    public Todo convertCreateTodoRequest2Todo(CreateTodoRequest createTodoRequest) throws UserNotFoundException {
        User user = userService.findByUsername(createTodoRequest.getUserName());

        return Todo.builder()
                .todoName(createTodoRequest.getTodoName())
                .description(createTodoRequest.getDescription())
                .targetDate(createTodoRequest.getTargetDate())
                .user(user)
                .isCompleted(createTodoRequest.getIsCompleted())
                .completedDate(Boolean.TRUE.equals(createTodoRequest.getIsCompleted()) ? new Date() : null)
                .build();
    }

    public CreateTodoResponse convertTodo2CreateTodoResponse(Todo todo) {
        return CreateTodoResponse.builder()
                .id(todo.getId())
                .todoName(todo.getTodoName())
                .createdDate(todo.getCreatedDate())
                .targetDate(todo.getTargetDate())
                .userName(todo.getUser().getUserName())
                .build();
    }

    public Todo convertUpdateTodoRequest2Todo(UpdateTodoRequest updateTodoRequest, Todo todo) throws UserNotFoundException {
        User user = userService.findByUsername(updateTodoRequest.getUserName());

        return Todo.builder()
                .id(todo.getId())
                .todoName(updateTodoRequest.getTodoName())
                .description(updateTodoRequest.getDescription())
                .isCompleted(updateTodoRequest.getIsCompleted())
                .targetDate(updateTodoRequest.getTargetDate())
                .user(user)
                .lastUpdatedDate(todo.getLastUpdatedDate())
                .createdDate(todo.getCreatedDate())
                .completedDate(Boolean.TRUE.equals(updateTodoRequest.getIsCompleted()) ? new Date() : null)
                .build();
    }

    public UpdateTodoResponse convertTodo2UpdateTodoResponse(Todo updatedTodo) {
        return UpdateTodoResponse.builder()
                .id(updatedTodo.getId())
                .todoName(updatedTodo.getTodoName())
                .lastUpdatedDate(updatedTodo.getLastUpdatedDate())
                .isCompleted(updatedTodo.getIsCompleted())
                .userName(updatedTodo.getUser().getUserName())
                .createdDate(updatedTodo.getCreatedDate())
                .build();
    }

    public TodoResponse convertTodo2TodoResponse(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .todoName(todo.getTodoName())
                .description(todo.getDescription())
                .userName(todo.getUser().getUserName())
                .completedDate(todo.getCompletedDate())
                .targetDate(todo.getTargetDate())
                .lastUpdatedDate(todo.getLastUpdatedDate())
                .isCompleted(todo.getIsCompleted())
                .build();
    }
}
