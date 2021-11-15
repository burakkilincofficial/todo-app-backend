package com.bk.todoappbackend.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "todo_name")
    private String todoName;

    @Column(name = "description")
    private String description;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @Column(name = "completed_date")
    private Date completedDate;

    @Column(name = "target_date")
    private Date targetDate;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;


}
