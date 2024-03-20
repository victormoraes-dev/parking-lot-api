package dev.victormoraes.adapters.in.dtos.user;

import dev.victormoraes.domain.users.UserType;

import java.util.List;

public class UserRequestDTO extends UserDTO {

    private UserType userType;
    private List<String> roles;

    public UserRequestDTO(String username, String password, UserType userType) {
        super(username, password);
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
