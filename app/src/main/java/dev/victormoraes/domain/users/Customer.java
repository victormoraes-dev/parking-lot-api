package dev.victormoraes.domain.users;

import java.util.List;

public class Customer extends User {
    public Customer(String username) {
        super(username);
    }

    public Customer(Long userId, String username, String password, String firstName, String lastName, List<String> roles) {
        super(userId, username, password, firstName, lastName, UserType.CUSTOMER, roles);
    }


    public Customer(Long userId, String username) {
        super(userId, username);
    }

    public Customer() {
    }
}
