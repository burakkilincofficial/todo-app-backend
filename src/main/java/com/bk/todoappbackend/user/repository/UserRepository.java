package com.bk.todoappbackend.user.repository;

import com.bk.todoappbackend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO Describe this class.
 * @since 2021-11-18
 * @author burak kilinc
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    List<User> findAllByUserName(String userName);

    User findByUserName(String userName);
}
