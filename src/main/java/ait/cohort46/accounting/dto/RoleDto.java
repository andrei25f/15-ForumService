package ait.cohort46.accounting.dto;

import lombok.*;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
    private String login;
    @Singular
    private Set<String> roles;
}
