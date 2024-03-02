package dev.victormoraes.domain.users;

public class Anonymous extends Customer {
    public Anonymous(Long userId, String username) {
        super.setUserId(userId);
        super.setUsername(username);
    }

    public Anonymous() {
    }
}
