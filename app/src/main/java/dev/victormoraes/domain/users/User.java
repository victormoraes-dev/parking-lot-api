package dev.victormoraes.domain.users;

import java.util.List;

public class User {

    private Long userId;
    private String username;

    private String password;
    private String firstName;
    private String lastName;

    private UserType userType;

    private List<String> roles;


    public User(String username) {
        this.username = username;
    }

    public User(Long userId, String username, String password, String firstName, String lastName, UserType userType, List<String> roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.roles = roles;
    }

    public User(String username, String password, UserType userType, List<String> roles) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.roles = roles;
    }

    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public User(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
