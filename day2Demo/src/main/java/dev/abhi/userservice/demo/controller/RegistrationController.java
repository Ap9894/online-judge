package dev.abhi.userservice.demo.controller;

import dev.abhi.userservice.demo.Service.UserService;
import dev.abhi.userservice.demo.dto.ResponseDto;
import dev.abhi.userservice.demo.dto.userDto;
import dev.abhi.userservice.demo.dto.userResponseDto;
import dev.abhi.userservice.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//Rest Controller allows to send JSON kind of data
// TODO: Controller vs Rest Controller
@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseDto<userResponseDto> registerUser(@RequestBody userDto userdto){
        User newUser = userService.registerUser(userdto);
        return new ResponseDto<>(
                new userResponseDto(newUser.getId(), newUser.getFullname(),newUser.getEmail(), newUser.isActive()),
                HttpStatus.OK);

    }

    //after successful registration a token is generated for the user and sent via email
    //when the user clicks on the link provided in the email the corresponding GET mapping is generated to validate the user

    // example get url:   "/user/confirm?token=32432432423"
    @GetMapping("/user/confirm")
    public ResponseDto<userResponseDto> validateUser(@RequestParam String token){
        User validatedUser = userService.validateUser(token);
        return new ResponseDto<>(
                new userResponseDto(validatedUser.getId(), validatedUser.getFullname(),validatedUser.getEmail(), validatedUser.isActive()),
                HttpStatus.OK);
    }
}
