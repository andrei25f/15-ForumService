package ait.cohort46.accounting.service;

import ait.cohort46.accounting.dao.UserRepository;
import ait.cohort46.accounting.dto.RegisterDto;
import ait.cohort46.accounting.dto.UpdateUserDto;
import ait.cohort46.accounting.dto.UserDto;
import ait.cohort46.accounting.dto.exception.UserAlreadyExistsException;
import ait.cohort46.accounting.dto.exception.UserNotFoundException;
import ait.cohort46.accounting.model.User;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto registerUser(RegisterDto registerDto) {
        if (userRepository.existsUserByLoginIgnoreCase(registerDto.getLogin())) {
            throw new UserAlreadyExistsException();
        }
        User user = modelMapper.map(registerDto, User.class);
        String password = BCrypt.hashpw(registerDto.getPassword(), BCrypt.gensalt());
        user.setPassword(password);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto removeUser(String id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(String id, UpdateUserDto updateUserDto) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (updateUserDto.getFirstName() != null) {
            user.setFirstName(updateUserDto.getFirstName());
        }
        if (updateUserDto.getLastName() != null) {
            user.setLastName(updateUserDto.getLastName());
        }
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto addRole(String id, String role) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (role != null) {
            user.addRole(role);
        }
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto removeRole(String id, String role) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (role != null) {
            user.removeRole(role);
        }
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void changePassword(String id, String newPassword) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        user = userRepository.save(user);
    }
}
