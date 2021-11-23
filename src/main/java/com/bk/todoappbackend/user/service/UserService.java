package com.bk.todoappbackend.user.service;

import com.bk.todoappbackend.user.entity.User;
import com.bk.todoappbackend.user.exception.UserNameAlreadyExistException;
import com.bk.todoappbackend.user.exception.UserNotFoundException;
import com.bk.todoappbackend.user.model.CreateUserRequest;
import com.bk.todoappbackend.user.model.UpdateUserRequest;
import com.bk.todoappbackend.user.model.response.AllUserResponse;
import com.bk.todoappbackend.user.model.response.CreateUserResponse;
import com.bk.todoappbackend.user.model.response.UpdateUserResponse;

/**
 * TODO Describe this class.
 * @since 2021-11-18
 * @author burak kilinc
 */
public interface UserService {
    AllUserResponse getAllUsers();

    CreateUserResponse createNewUser(CreateUserRequest createUserRequest) throws Exception;

    AllUserResponse getAllUsersByUsername(String name);

    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest, Integer id) throws UserNotFoundException;

    User findById(Integer id) throws UserNotFoundException;

    User findByUsername(String username) throws UserNotFoundException;

    void deleteById(Integer id) throws UserNotFoundException;
}
