package com.task_manager.manager.entity;

        import com.fasterxml.jackson.annotation.JsonIgnore;
        import lombok.Data;

        import javax.persistence.*;
@Data
@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne( fetch = FetchType.EAGER)
    @JoinColumn(name= "user_id", nullable = false)
    @JsonIgnore
    private User user;
}