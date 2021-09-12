package com.bk.todoappbackend.todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTodoRequest {
    private String todoName;
    private String description;
    private Date targetDate;
}
