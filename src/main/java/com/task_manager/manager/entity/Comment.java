package com.task_manager.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"task", "user"})
@ToString(exclude = {"task", "user"})
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    @JsonIgnore
    private Task task;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
