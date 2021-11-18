package com.bk.todoappbackend.todo.repository;

import com.bk.todoappbackend.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findAll();

    List<Todo> findAllByUser_UserName(String userName);
}
