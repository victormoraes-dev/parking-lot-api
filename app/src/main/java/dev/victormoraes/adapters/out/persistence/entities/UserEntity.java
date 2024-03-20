package dev.victormoraes.adapters.out.persistence.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles;

    public UserEntity(String username, String firstName, String lastName) {
        this.userId = Math.abs(new Random().nextLong());
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserEntity(String username) {
        this.userId = Math.abs(new Random().nextLong());
        this.username = username + "-" + userId;
    }

    public UserEntity() {
    }

    @Entity
    @DiscriminatorValue("ADMIN")
    public static class AdminEntity extends UserEntity {
        public AdminEntity(String username, String firstName, String lastName) {
            super(username, firstName, lastName);
        }

        public AdminEntity() {
            super();
        }
    }

    @Entity
    @DiscriminatorValue("ANONYMOUS")
    public static class AnonymousEntity extends UserEntity {

        public AnonymousEntity() {
            super("anonymous");
        }

    }

    @Entity
    @DiscriminatorValue("CUSTOMER")
    public static class CustomerEntity extends UserEntity {
        public CustomerEntity(String username, String firstName, String lastName) {
            super(username, firstName, lastName);
        }

        public CustomerEntity() {
            super();
        }
    }

    @Entity
    @DiscriminatorValue("OPERATOR")
    public static class OperatorEntity extends UserEntity {
        public OperatorEntity(String username, String firstName, String lastName) {
            super(username, firstName, lastName);
        }

        public OperatorEntity() {
            super();
        }
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}
