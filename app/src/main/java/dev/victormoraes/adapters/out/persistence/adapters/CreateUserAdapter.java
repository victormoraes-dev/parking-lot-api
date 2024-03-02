package dev.victormoraes.adapters.out.persistence.adapters;

import dev.victormoraes.adapters.mappers.UserMapper;
import dev.victormoraes.adapters.out.persistence.repositories.UserRepository;
import dev.victormoraes.domain.users.User;
import dev.victormoraes.usecases.ports.CreateUserPort;
import org.springframework.stereotype.Component;

@Component
public class CreateUserAdapter implements CreateUserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public CreateUserAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User createUser(User user) {

        var userEntity = userMapper.toEntityModel(user);
        var newUser = userRepository.save(userEntity);

        return userMapper.toDomainModel(newUser);
    }
}
