package com.task_manager.manager.entity.DTO;

import com.task_manager.manager.entity.Authority;
import com.task_manager.manager.entity.User;
import com.task_manager.manager.entity.enums.Role;
import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String surname;
    private Role role;
    private String email;

    public static UserDTO convertToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(Role.valueOf(user.getAuthority().getRole()));
        return userDTO;
    }
}
