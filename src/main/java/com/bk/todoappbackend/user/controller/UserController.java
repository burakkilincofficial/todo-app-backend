package com.bk.todoappbackend.user.controller;

import com.bk.todoappbackend.todo.exception.TodoNotFoundException;
import com.bk.todoappbackend.user.entity.User;
import com.bk.todoappbackend.user.exception.UserNameAlreadyExistException;
import com.bk.todoappbackend.user.exception.UserNotFoundException;
import com.bk.todoappbackend.user.model.CreateUserRequest;
import com.bk.todoappbackend.user.model.UpdateUserRequest;
import com.bk.todoappbackend.user.model.response.AllUserResponse;
import com.bk.todoappbackend.user.model.response.CreateUserResponse;
import com.bk.todoappbackend.user.model.response.UpdateUserResponse;
import com.bk.todoappbackend.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public AllUserResponse getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{name}")
    public AllUserResponse getAllTodos(@PathVariable("name") String name) {
        return userService.getAllUsersByUsername(name);
    }

    @PostMapping("")
    public CreateUserResponse createNewTodo(@RequestBody CreateUserRequest createUserRequest) throws UserNotFoundException, UserNameAlreadyExistException {
        return userService.createNewTodo(createUserRequest);
    }

    @GetMapping(path = "/{id}")
    public User getTodo(@PathVariable("id") Integer id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public UpdateUserResponse updateTodo(@PathVariable Integer id, @RequestBody UpdateUserRequest updateUserRequest) throws TodoNotFoundException, UserNotFoundException {
        return userService.updateUser(updateUserRequest, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Integer id) throws UserNotFoundException {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
