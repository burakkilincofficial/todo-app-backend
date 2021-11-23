package com.bk.todoappbackend.user.service;

import com.bk.todoappbackend.common.security.jwtauth.JwtInMemoryUserDetailsService;
import com.bk.todoappbackend.user.entity.User;
import com.bk.todoappbackend.user.exception.UserNameAlreadyExistException;
import com.bk.todoappbackend.user.exception.UserNotFoundException;
import com.bk.todoappbackend.user.mapper.UserMapper;
import com.bk.todoappbackend.user.model.CreateUserRequest;
import com.bk.todoappbackend.user.model.UpdateUserRequest;
import com.bk.todoappbackend.user.model.response.AllUserResponse;
import com.bk.todoappbackend.user.model.response.CreateUserResponse;
import com.bk.todoappbackend.user.model.response.UpdateUserResponse;
import com.bk.todoappbackend.user.model.response.UserResponse;
import com.bk.todoappbackend.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Describe this class.
 * @since 2021-11-18
 * @author burak kilinc
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final JwtInMemoryUserDetailsService jwtInMemoryUserDetailsService;

    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, JwtInMemoryUserDetailsService jwtInMemoryUserDetailsService, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
        this.encoder = encoder;
    }

    @Override
    public AllUserResponse getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        allUsers.forEach(user -> userResponses.add(userMapper.convertUser2UserResponse(user)));
        return AllUserResponse.builder()
                .data(userResponses)
                .todoCount(userResponses.size())
                .build();
    }

    @Override
    public CreateUserResponse createNewUser(CreateUserRequest createUserRequest) throws Exception {
        checkUserNameIfExist(createUserRequest.getUserName());
        User user = userMapper.convertCreateUserRequest2User(createUserRequest);
        User savedUser = userRepository.save(user);

        if (!addNewUser2AuthenticatedUsers(createUserRequest)) {
            throw new Exception("There is while creating new user, try again later!");
        }

        return userMapper.convertUser2CreateUserResponse(savedUser);
    }

    private boolean addNewUser2AuthenticatedUsers(CreateUserRequest createUserRequest) {
        String encodeString = encoder.encode(createUserRequest.getPassword());
        return jwtInMemoryUserDetailsService.addUserToAuthenticatedUsers(createUserRequest.getUserName(), encodeString);
    }

    private void checkUserNameIfExist(String userName) throws UserNameAlreadyExistException {
        User byUserName = userRepository.findByUserName(userName);
        if (byUserName != null) {
            throw new UserNameAlreadyExistException(String.format("user name '%s' already exists, please select new one", userName));
        }
    }

    @Override
    public AllUserResponse getAllUsersByUsername(String name) {
        List<User> allUsersByUserName = userRepository.findAllByUserName(name);
        List<UserResponse> userResponses = new ArrayList<>();
        allUsersByUserName.forEach(todo -> userResponses.add(userMapper.convertUser2UserResponse(todo)));
        return AllUserResponse.builder()
                .data(userResponses)
                .todoCount(userResponses.size())
                .build();
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest, Integer id) throws UserNotFoundException {
        checkUserIfExist(id);
        User user = userMapper.convertUpdateUserRequest2User(updateUserRequest, id);
        User updatedUser = userRepository.save(user);
        return userMapper.convertUser2UpdateUserResponse(updatedUser);
    }

    @Override
    public User findById(Integer id) throws UserNotFoundException {
        return checkUserIfExist(id);
    }

    @Override
    public User findByUsername(String username) throws UserNotFoundException {
        User byUserName = userRepository.findByUserName(username);
        if (byUserName == null) {
            throw new UserNotFoundException(String.format("user not found, username: %s", username));
        }
        return byUserName;
    }

    private User checkUserIfExist(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("user not found, id: %s", id)));

    }

    @Override
    public void deleteById(Integer id) throws UserNotFoundException {
        findById(id);
        userRepository.deleteById(id);
    }
}
