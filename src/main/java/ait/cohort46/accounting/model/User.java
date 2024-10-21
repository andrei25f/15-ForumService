package ait.cohort46.accounting.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = "login")
@Document(collection = "users")
@NoArgsConstructor
public class User {
    @Id
    private String login;
    @Setter
    private String password;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    private Set<Role> roles = new HashSet<>() {{ add(Role.USER); }};

    public User(String login, String password, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean addRole(String role) {
        return roles.add(Role.valueOf(role.toUpperCase()));
    }

    public boolean removeRole(String role) {
        return roles.remove(Role.valueOf(role.toUpperCase()));
    }
}
