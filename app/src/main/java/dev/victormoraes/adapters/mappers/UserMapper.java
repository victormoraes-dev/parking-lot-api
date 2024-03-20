package dev.victormoraes.adapters.mappers;

import dev.victormoraes.adapters.in.dtos.user.UserRequestDTO;
import dev.victormoraes.adapters.in.dtos.user.UserResponseDTO;
import dev.victormoraes.adapters.out.persistence.entities.RoleEntity;
import dev.victormoraes.adapters.out.persistence.entities.UserEntity;
import dev.victormoraes.adapters.out.persistence.entities.UserEntity.AdminEntity;
import dev.victormoraes.adapters.out.persistence.entities.UserEntity.AnonymousEntity;
import dev.victormoraes.adapters.out.persistence.entities.UserEntity.CustomerEntity;
import dev.victormoraes.adapters.out.persistence.entities.UserEntity.OperatorEntity;
import dev.victormoraes.domain.users.*;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntityModel(User user) {

        switch (user) {
            case Admin admin -> {
                return new AdminEntity(admin.getUsername(), admin.getFirstName(), admin.getLastName());
            }
            case Anonymous anonymous -> {
                return new AnonymousEntity();

            }
            case Customer customer -> {
                return new CustomerEntity(customer.getUsername(), customer.getFirstName(), customer.getLastName());

            }
            case Operator operator -> {
                return new OperatorEntity(operator.getUsername(), operator.getFirstName(), operator.getLastName());

            }
            default -> throw new IllegalArgumentException("No user entity was found to instantiate");
        }
    }

    public User toDomainModel(UserEntity userEntity) {


        switch (userEntity) {
            case AdminEntity admin -> {
                return new Admin(admin.getUserId(),
                        admin.getUsername(),
                        admin.getPassword(),
                        admin.getFirstName(),
                        admin.getLastName(),
                        admin.getRoles()
                                .stream()
                                .map(RoleEntity::getName)
                                .toList());
            }
            case AnonymousEntity anonymous -> {
                return new Anonymous(anonymous.getUserId(), anonymous.getUsername());

            }
            case CustomerEntity customer -> {
                return new Customer(customer.getUserId(),
                        customer.getUsername(),
                        customer.getPassword(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getRoles()
                                .stream()
                                .map(RoleEntity::getName)
                                .toList());

            }
            case OperatorEntity operator -> {
                return new Operator(operator.getUserId(),
                        operator.getUsername(),
                        operator.getPassword(),
                        operator.getFirstName(),
                        operator.getLastName(),
                        operator.getRoles()
                                .stream()
                                .map(RoleEntity::getName)
                                .toList());

            }
            default -> throw new IllegalArgumentException("No user entity was found to instantiate");
        }


    }

    public User toDomainModel(UserRequestDTO userRequestDTO) {

        return new User(userRequestDTO.getUsername(), userRequestDTO.getPassword(), userRequestDTO.getUserType(), userRequestDTO.getRoles());
    }

    public UserResponseDTO toResponseDTO(User user) {

        return new UserResponseDTO(user.getUsername(), user.getUserId(), user.getRoles());
    }
}
