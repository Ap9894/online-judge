package dev.abhi.userservice.demo.Service;

import dev.abhi.userservice.demo.dto.UpdatePasswordDto;
import dev.abhi.userservice.demo.dto.userDto;
import dev.abhi.userservice.demo.model.User;

public interface UserService {

    public User registerUser(userDto userdto);

    public User validateUser(String token);

    public User generatePasswordResetToken(userDto userDto);

    public User validatePasswordResetToken(String token);

    public User updatePassword(UpdatePasswordDto updatePasswordDto);

}
