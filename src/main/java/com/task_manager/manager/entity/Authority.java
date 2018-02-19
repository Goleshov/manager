package com.task_manager.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"users"})
@ToString(exclude = {"users"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String role;

    @OneToMany(mappedBy = "authority", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @Override
    @JsonIgnore
    public String getAuthority() {
        return getRole();
    }

}
