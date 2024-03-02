package dev.victormoraes.domain.users;

public class Customer extends User{
    public Customer(String username) {
        super(username);
    }

    public Customer(Long userId, String username, String firstName, String lastName) {
        super(userId, username, firstName, lastName);
    }

    public Customer() {
    }
}
