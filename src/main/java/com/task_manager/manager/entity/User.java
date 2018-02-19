package com.task_manager.manager.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(exclude = {"projects", "authority","comments", "tasks"})
@ToString(exclude = {"projects", "authority","comments", "tasks"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    @Column
    private String surname;
    @Email
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private Boolean isActivated;
    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnore
    private Authority authority = new Authority();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_project",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_task",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    @JsonIgnore
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

}
