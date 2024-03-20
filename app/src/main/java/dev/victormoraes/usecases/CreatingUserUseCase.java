package dev.victormoraes.usecases;

import dev.victormoraes.domain.result.Result;
import dev.victormoraes.domain.users.User;
import dev.victormoraes.usecases.ports.CreateUserPort;
import dev.victormoraes.usecases.ports.GetUserPort;
import org.springframework.stereotype.Component;

@Component
public class CreatingUserUseCase {

    private final CreateUserPort createUserPort;
    private final GetUserPort getUserPort;

    public CreatingUserUseCase(CreateUserPort createUserPort, GetUserPort getUserPort) {
        this.createUserPort = createUserPort;
        this.getUserPort = getUserPort;
    }

    public Result<User> createUser(User user) {

        var userByUsername = getUserPort.findUserByUsername(user.getUsername());

        if (userByUsername.isPresent()) {
            var result = new Result<User>(false);
            result.setErrorMessage(String.format("There is already a user with the given username: %s", user.getUsername()));
            return result;
        }

        var createdUser = createUserPort.createUser(user);
        var result = new Result<User>(true);
        result.setResult(createdUser);
        return result;
    }
}
