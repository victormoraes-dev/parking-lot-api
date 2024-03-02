package dev.victormoraes.domain.users;

public class Admin extends User{
    public Admin(Long userId, String username, String firstName, String lastName) {
        super(userId, username, firstName, lastName);
    }
}
