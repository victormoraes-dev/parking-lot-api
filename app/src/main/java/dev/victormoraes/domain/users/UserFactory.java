package dev.victormoraes.domain.users;

import java.util.Map;
import java.util.function.Supplier;

public class UserFactory {

    private static final Map<UserType, Supplier<User>> userFactory = Map.of(
            UserType.ADMIN, Admin::new,
            UserType.CUSTOMER, Customer::new,
            UserType.OPERATOR, Operator::new,
            UserType.ANONYMOUS, Anonymous::new
    );

    public static User getUser(UserType userType) {

        Supplier<User> factory = userFactory.get(userType);

        if (factory != null) {
            return factory.get();
        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }
}
