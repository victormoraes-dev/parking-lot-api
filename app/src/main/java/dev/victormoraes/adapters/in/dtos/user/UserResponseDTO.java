package dev.victormoraes.adapters.in.dtos.user;

import java.util.List;

public record UserResponseDTO(String username, Long userId, List<String> roles) {

}
