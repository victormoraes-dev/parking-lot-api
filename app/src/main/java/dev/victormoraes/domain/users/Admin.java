package dev.victormoraes.domain.users;

import java.util.List;

public class Admin extends User{
    public Admin(Long userId, String username, String password, String firstName, String lastName, List<String> roles) {
        super(userId, username, password, firstName, lastName, UserType.ADMIN, roles);
    }

    public Admin() {
    }
}
