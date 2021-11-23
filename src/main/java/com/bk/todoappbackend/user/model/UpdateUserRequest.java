package com.bk.todoappbackend.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private String userName;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private Date birthDate;
}
