package dev.victormoraes.domain.users;

import dev.victormoraes.adapters.mappers.visitors.IUserVisitor;

public class Customer extends User {
    public Customer(String username) {
        super(username);
    }

    public Customer(Long userId, String username, String firstName, String lastName) {
        super(userId, username, firstName, lastName);
    }

    public Customer() {
    }

    @Override
    public <T> T accept(IUserVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
