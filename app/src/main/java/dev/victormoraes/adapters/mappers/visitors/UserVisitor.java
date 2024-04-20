package dev.victormoraes.adapters.mappers.visitors;

import dev.victormoraes.adapters.out.persistence.entities.UserEntity;
import dev.victormoraes.domain.users.Admin;
import dev.victormoraes.domain.users.Anonymous;
import dev.victormoraes.domain.users.Customer;
import dev.victormoraes.domain.users.Operator;

public class UserVisitor implements IUserVisitor<UserEntity> {

    @Override
    public UserEntity visit(Admin admin) {
        return new UserEntity.AdminEntity(admin.getUsername(), admin.getFirstName(), admin.getLastName());
    }

    @Override
    public UserEntity visit(Anonymous anonymous) {
        return new UserEntity.AnonymousEntity();
    }

    @Override
    public UserEntity visit(Customer customer) {
        return new UserEntity.CustomerEntity(customer.getUsername(), customer.getFirstName(), customer.getLastName());

    }

    @Override
    public UserEntity visit(Operator operator) {
        return new UserEntity.OperatorEntity(operator.getUsername(), operator.getFirstName(), operator.getLastName());

    }
}

