package dev.victormoraes.adapters.mappers.visitors;

import dev.victormoraes.adapters.out.persistence.entities.UserEntity;

public interface IUserEntityVisitor<T> {
    T visit(UserEntity.AdminEntity admin);

    T visit(UserEntity.AnonymousEntity anonymous);

    T visit(UserEntity.CustomerEntity customer);

    T visit(UserEntity.OperatorEntity operator);
}
