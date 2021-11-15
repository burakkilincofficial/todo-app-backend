package com.bk.todoappbackend.todo.mapper;

import com.bk.todoappbackend.todo.entity.Todo;
import com.bk.todoappbackend.todo.model.CreateTodoRequest;
import com.bk.todoappbackend.todo.model.UpdateTodoRequest;
import com.bk.todoappbackend.todo.model.response.CreateTodoResponse;
import com.bk.todoappbackend.todo.model.response.TodoResponse;
import com.bk.todoappbackend.todo.model.response.UpdateTodoResponse;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public Todo convertCreateTodoRequest2Todo(CreateTodoRequest createTodoRequest) {
        return Todo.builder()
                .todoName(createTodoRequest.getTodoName())
                .description(createTodoRequest.getDescription())
                .targetDate(createTodoRequest.getTargetDate())
                .userName(createTodoRequest.getUserName())
                .build();
    }

    public CreateTodoResponse convertTodo2CreateTodoResponse(Todo todo) {
        return CreateTodoResponse.builder()
                .id(todo.getId())
                .todoName(todo.getTodoName())
                .createdDate(todo.getCreatedDate())
                .userName(todo.getUserName())
                .build();
    }

    public Todo convertUpdateTodoRequest2Todo(UpdateTodoRequest updateTodoRequest, Integer id) {
        return Todo.builder()
                .id(id)
                .todoName(updateTodoRequest.getTodoName())
                .description(updateTodoRequest.getDescription())
                .isCompleted(updateTodoRequest.getIsCompleted() != null)
                .targetDate(updateTodoRequest.getTargetDate())
                .userName(updateTodoRequest.getUserName())
                .build();
    }

    public UpdateTodoResponse convertTodo2UpdateTodoResponse(Todo updatedTodo) {
        return UpdateTodoResponse.builder()
                .id(updatedTodo.getId())
                .todoName(updatedTodo.getTodoName())
                .lastUpdatedDate(updatedTodo.getLastUpdatedDate())
                .isCompleted(updatedTodo.isCompleted())
                .userName(updatedTodo.getUserName())
                .build();
    }

    public TodoResponse convertTodo2TodoResponse(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .todoName(todo.getTodoName())
                .description(todo.getDescription())
                .userName(todo.getUserName())
                .completedDate(todo.getCompletedDate())
                .targetDate(todo.getTargetDate())
                .lastUpdatedDate(todo.getLastUpdatedDate())
                .isCompleted(todo.isCompleted())
                .build();
    }
}
