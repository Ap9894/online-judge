package dev.abhi.userservice.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class userResponseDto {

    private Long id;

    private String email;

    private String fullname;

    private boolean active;

    public userResponseDto(Long id, String email, String fullname, boolean active) {
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.active = active;
    }
}
