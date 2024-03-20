package dev.victormoraes.domain.users;

import java.util.List;

public class Operator extends User {

    public Operator(Long userId, String username, String password, String firstName, String lastName, List<String> roles) {
        super(userId, username, password, firstName, lastName, UserType.OPERATOR, roles);
    }

    public Operator() {
    }
}
