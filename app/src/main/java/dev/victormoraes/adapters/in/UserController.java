package dev.victormoraes.adapters.in;

import dev.victormoraes.adapters.in.dtos.ResponseWrapper;
import dev.victormoraes.adapters.in.dtos.user.UserRequestDTO;
import dev.victormoraes.adapters.in.dtos.user.UserResponseDTO;
import dev.victormoraes.adapters.mappers.UserMapper;
import dev.victormoraes.domain.users.User;
import dev.victormoraes.domain.users.UserFactory;
import dev.victormoraes.usecases.CreatingUserUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/users/auth")
public class UserController {

    private final CreatingUserUseCase createUserUseCase;
    private final UserMapper userMapper;

    private final HttpServletRequest request;

    public UserController(CreatingUserUseCase createUserUseCase, UserMapper userMapper, HttpServletRequest request) {
        this.createUserUseCase = createUserUseCase;
        this.userMapper = userMapper;
        this.request = request;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<UserResponseDTO>> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) throws URISyntaxException {

        User user = UserFactory.getUser(userRequestDTO.getUserType());
        user.setUserType(userRequestDTO.getUserType());
        user.setRoles(userRequestDTO.getRoles());
        user.setPassword(userRequestDTO.getPassword());
        user.setUsername(userRequestDTO.getUsername());

        var result = createUserUseCase.createUser(user);

        if (!result.isSuccess()) {
            return ResponseEntity.unprocessableEntity().body(ResponseWrapper.error(result.getErrorMessage()));
        }

        UserResponseDTO userResponseDTO = userMapper.toResponseDTO(result.getResult());

        String currentUrl = request.getRequestURL().toString();

        return ResponseEntity.created(new URI(currentUrl + "/" + userResponseDTO.userId()))
                .body(ResponseWrapper.success(userResponseDTO));
    }
}
