package dev.victormoraes.adapters.out.persistence.adapters;

import dev.victormoraes.adapters.mappers.UserMapper;
import dev.victormoraes.adapters.out.persistence.repositories.UserRepository;
import dev.victormoraes.domain.users.User;
import dev.victormoraes.usecases.ports.GetUserPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetUserAdapter implements GetUserPort {

    private final UserRepository userRepository;

    public GetUserAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {

        var user = userRepository.findByUsername(username);
        return user.map(UserMapper::toDomainModel);
    }
}
