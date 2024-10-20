package ait.cohort46.accounting.dto;

import lombok.*;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
