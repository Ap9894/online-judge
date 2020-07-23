package dev.abhi.userservice.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdatePasswordDto {

    //TODO: implement custom validators for email and password
    @NotEmpty
    @Size(min=1)
    private String email;

    @NotEmpty
    @Size(min = 5)
    private String token;

    @NotEmpty
    @Size(min = 5)
    private String newPassword;
}
