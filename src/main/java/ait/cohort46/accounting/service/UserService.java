package ait.cohort46.accounting.service;

import ait.cohort46.accounting.dto.RegisterDto;
import ait.cohort46.accounting.dto.UpdateUserDto;
import ait.cohort46.accounting.dto.UserDto;

public interface UserService {
    UserDto registerUser(RegisterDto registerDto);

    UserDto removeUser(String id);

    UserDto updateUser(String id, UpdateUserDto updateUserDto);

    UserDto addRole(String id, String role);

    UserDto removeRole(String id, String role);

    UserDto getUser(String id);

    void changePassword(String id, String newPassword);
}
