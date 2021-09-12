package com.bk.todoappbackend.todo.controller;

import com.bk.todoappbackend.todo.model.response.TodoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllTodoResponse {
    private List<TodoResponse> data;
    private Integer todoCount;
}
