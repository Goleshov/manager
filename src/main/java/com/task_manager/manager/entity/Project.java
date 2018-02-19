package com.task_manager.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"tasks", "users"})
@ToString(exclude = {"tasks", "users"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Task> tasks = new ArrayList<>();


}
