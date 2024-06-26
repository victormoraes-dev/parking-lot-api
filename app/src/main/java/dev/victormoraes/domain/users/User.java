package dev.victormoraes.domain.users;

import dev.victormoraes.adapters.mappers.visitors.IUserEntityVisitor;
import dev.victormoraes.adapters.mappers.visitors.IUserVisitor;

public abstract class User {

    private Long userId;
    private String username;
    private String firstName;
    private String lastName;

    public User(String username) {
        this.username = username;
    }

    public User(Long userId, String username, String firstName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
    }

    public abstract <T> T accept(IUserVisitor<T> visitor);

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
}
