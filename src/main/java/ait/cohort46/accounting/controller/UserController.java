package ait.cohort46.accounting.controller;

import ait.cohort46.accounting.dto.RegisterDto;
import ait.cohort46.accounting.dto.UpdateUserDto;
import ait.cohort46.accounting.dto.UserDto;
import ait.cohort46.accounting.service.UserService;
import ait.cohort46.post.dto.NewPostDto;
import ait.cohort46.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody RegisterDto registerDto) {
        return userService.registerUser(registerDto);
    }

    @PostMapping("/login")
    public UserDto login(Principal principal) {
        return userService.getUser(principal.getName());
    }

    @DeleteMapping("/user/{id}")
    public UserDto removeUser(@PathVariable String id) {
        return userService.removeUser(id);
    }

    @PatchMapping("/user/{id}")
    public UserDto updateUser(@PathVariable String id, @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(id, updateUserDto);
    }

    @PatchMapping("/user/{id}/role/{role}")
    public UserDto addRole(@PathVariable String id, @PathVariable String role) {
        return userService.addRole(id, role);
    }

    @DeleteMapping("/user/{id}/role/{role}")
    public UserDto removeRole(@PathVariable String id, @PathVariable String role) {
        return userService.removeRole(id, role);
    }

    @GetMapping("/user/{id}")
    public UserDto removeRole(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(Principal principal, @RequestHeader("X-Password") String newPassword) {
        userService.changePassword(principal.getName(), newPassword);
    }
}
