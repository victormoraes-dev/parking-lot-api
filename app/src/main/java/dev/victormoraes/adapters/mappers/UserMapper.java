package dev.victormoraes.adapters.mappers;

import dev.victormoraes.adapters.mappers.visitors.IUserEntityVisitor;
import dev.victormoraes.adapters.mappers.visitors.UserEntityVisitor;
import dev.victormoraes.adapters.mappers.visitors.UserVisitor;
import dev.victormoraes.adapters.out.persistence.entities.UserEntity;
import dev.victormoraes.domain.users.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntityModel(User user) {

        var userVisitor = new UserVisitor();
        return user.accept(userVisitor);
    }

    public User toDomainModel(UserEntity userEntity) {
        IUserEntityVisitor<User> visitor = new UserEntityVisitor();
        return userEntity.accept(visitor);
    }
}
