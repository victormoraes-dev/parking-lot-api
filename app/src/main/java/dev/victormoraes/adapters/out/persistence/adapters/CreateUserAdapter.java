package dev.victormoraes.adapters.out.persistence.adapters;

import dev.victormoraes.adapters.mappers.UserMapper;
import dev.victormoraes.adapters.out.persistence.repositories.RoleRepository;
import dev.victormoraes.adapters.out.persistence.repositories.UserRepository;
import dev.victormoraes.domain.users.User;
import dev.victormoraes.usecases.ports.CreateUserPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class CreateUserAdapter implements CreateUserPort {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public CreateUserAdapter(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {

        var userEntity = userMapper.toEntityModel(user);
        userEntity.setRoles(user.getRoles()
                .stream()
                .map(role -> roleRepository.findByName(role)
                        .orElseThrow(() -> new IllegalArgumentException(format("Invalid role: %s)", role))))
                .toList());

        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        var newUser = userRepository.save(userEntity);

        return userMapper.toDomainModel(newUser);
    }
}
