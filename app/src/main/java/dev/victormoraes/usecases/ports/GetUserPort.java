package dev.victormoraes.usecases.ports;

import dev.victormoraes.domain.users.User;

import java.util.Optional;

public interface GetUserPort {

    Optional<User> findUserByUsername(String username);
}
