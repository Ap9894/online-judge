package dev.abhi.userservice.demo.controller;

import dev.abhi.userservice.demo.Service.UserService;
import dev.abhi.userservice.demo.dto.ResponseDto;
import dev.abhi.userservice.demo.dto.UpdatePasswordDto;
import dev.abhi.userservice.demo.dto.userDto;
import dev.abhi.userservice.demo.dto.userResponseDto;
import dev.abhi.userservice.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PasswordResetController {
    //first generate a reset password token( simple
    //send an email containing the token

    @Autowired
    private UserService userService;

    @PostMapping("/user/resetPassword")
    public ResponseDto<userResponseDto> generatePasswordResetToken(@RequestBody userDto userdto){


        User user = userService.generatePasswordResetToken(userdto);

        return new ResponseDto<>(
             new userResponseDto(user.getId(),user.getEmail(),user.getFullname(),user.isActive()),
             HttpStatus.OK
        );
    }

    @GetMapping("/user/confirmPasswordReset")
    public ResponseDto<userResponseDto> validatePasswordResetToken(@RequestParam String token){

        User user = userService.validatePasswordResetToken(token);

        return new ResponseDto<>(
                new userResponseDto(user.getId(),user.getEmail(),user.getFullname(),user.isActive()),
                HttpStatus.OK
        );
    }

    @PostMapping("/user/newPassword")
    public ResponseDto<userResponseDto> updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto){

        User user = userService.updatePassword(updatePasswordDto);
        return new ResponseDto<>(
                new userResponseDto(user.getId(),user.getEmail(),user.getFullname(),user.isActive()),
                HttpStatus.OK
        );
    }

}
