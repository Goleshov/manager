package com.task_manager.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.task_manager.manager.entity.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(exclude = {"project", "comments", "users"})
@ToString(exclude = {"project", "comments", "users"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments=new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private List<User> users = new ArrayList<>();


}
