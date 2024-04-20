package dev.victormoraes.adapters.mappers.visitors;

import dev.victormoraes.domain.users.Admin;
import dev.victormoraes.domain.users.Anonymous;
import dev.victormoraes.domain.users.Customer;
import dev.victormoraes.domain.users.Operator;

public interface IUserVisitor<T> {

    T visit(Admin admin);

    T visit(Anonymous anonymous);

    T visit(Customer customer);

    T visit(Operator operator);
}
