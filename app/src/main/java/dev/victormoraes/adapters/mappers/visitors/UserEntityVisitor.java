package dev.victormoraes.adapters.mappers.visitors;

import dev.victormoraes.adapters.out.persistence.entities.UserEntity;
import dev.victormoraes.domain.users.*;

public class UserEntityVisitor implements IUserEntityVisitor<User> {
    @Override
    public User visit(UserEntity.AdminEntity admin) {
        return new Admin(admin.getUserId(), admin.getUsername(), admin.getFirstName(), admin.getLastName());
    }

    @Override
    public User visit(UserEntity.AnonymousEntity anonymous) {
        return new Anonymous();
    }

    @Override
    public User visit(UserEntity.CustomerEntity customer) {
        return new Customer(customer.getUserId(), customer.getUsername(), customer.getFirstName(), customer.getLastName());
    }

    @Override
    public User visit(UserEntity.OperatorEntity operator) {
        return new Operator(operator.getUserId(), operator.getUsername(), operator.getFirstName(), operator.getLastName());
    }
}

