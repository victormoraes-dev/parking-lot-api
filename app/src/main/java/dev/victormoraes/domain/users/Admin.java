package dev.victormoraes.domain.users;

import dev.victormoraes.adapters.mappers.visitors.IUserVisitor;

public class Admin extends User{
    public Admin(Long userId, String username, String firstName, String lastName) {
        super(userId, username, firstName, lastName);
    }

    @Override
    public <T> T accept(IUserVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
