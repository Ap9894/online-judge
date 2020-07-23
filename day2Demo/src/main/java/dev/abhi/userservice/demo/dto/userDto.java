package dev.abhi.userservice.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//spring will receive a network request and require getter,setter to create a userdto object
//we also want data received by the controller to be clean
//TODO:Implement custom validators for email and password

@Getter
@Setter
public class userDto {

    @NotEmpty
    @Size(min=5)
    private String fullname;


    @NotEmpty
    @Size(min=1)
    private String email;


    @NotEmpty
    @Size(min = 5)
    private String password;

}
