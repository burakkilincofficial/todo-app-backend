package com.bk.todoappbackend.user.mapper;

import com.bk.todoappbackend.user.entity.User;
import com.bk.todoappbackend.user.exception.UserNotFoundException;
import com.bk.todoappbackend.user.model.CreateUserRequest;
import com.bk.todoappbackend.user.model.UpdateUserRequest;
import com.bk.todoappbackend.user.model.response.CreateUserResponse;
import com.bk.todoappbackend.user.model.response.UpdateUserResponse;
import com.bk.todoappbackend.user.model.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * TODO Describe this class.
 * @since 2021-11-18
 * @author burak kilinc
 */
@Component
public class UserMapper {
    public User convertCreateUserRequest2User(CreateUserRequest createUserRequest) {

        return User.builder()
                .registerDate(new Date())
                .age(createUserRequest.getAge())
                .email(createUserRequest.getEmail())
                .userName(createUserRequest.getUserName())
                .password(createUserRequest.getPassword())
                .surname(createUserRequest.getSurname())
                .build();
    }

    public CreateUserResponse convertUser2CreateUserResponse(User user) {
        return CreateUserResponse.builder()
                .id(user.getId())
                .createdDate(user.getRegisterDate())
                .userName(user.getUserName())
                .build();
    }

    public User convertUpdateUserRequest2User(UpdateUserRequest updateUserRequest, Integer id) throws UserNotFoundException {

        return User.builder()
                .id(id)
                .registerDate(new Date())
                .age(updateUserRequest.getAge())
                .email(updateUserRequest.getEmail())
                .userName(updateUserRequest.getUserName())
                .password(updateUserRequest.getPassword())
                .surname(updateUserRequest.getSurname())
                .build();
    }

    public UpdateUserResponse convertUser2UpdateUserResponse(User updatedUser) {
        return UpdateUserResponse.builder()
                .id(updatedUser.getId())
                .lastUpdatedDate(updatedUser.getLastUpdatedDate())
                .userName(updatedUser.getUserName())
                .birthDate(updatedUser.getBirthDate())
                .build();
    }

    public UserResponse convertUser2UserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .lastUpdatedDate(user.getLastUpdatedDate())
                .birtDate(user.getBirthDate())
                .todos(user.getTodos())
                .build();
    }
}
