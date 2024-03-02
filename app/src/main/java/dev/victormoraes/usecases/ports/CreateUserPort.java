package dev.victormoraes.usecases.ports;

import dev.victormoraes.domain.users.User;

public interface CreateUserPort {

    User createUser(User user);
}
