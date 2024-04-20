package dev.victormoraes.domain.users;

import java.util.Random;

public class Anonymous extends Customer {

    public Anonymous(Long userId, String username) {
        super.setUserId(userId);
        super.setUsername(username);
    }

    public Anonymous() {
        Random random = new Random();
        super.setUsername("anonymous-" + random.nextLong());
    }

}
