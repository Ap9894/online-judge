package dev.abhi.userservice.demo.event;

import dev.abhi.userservice.demo.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

//this is just an event will be produced by a producer and listened by a listner
@Getter
@Setter
public class SuccessfullRegistration extends ApplicationEvent {

    private final User registeredUser;

    public SuccessfullRegistration(User registeredUser) {
        super(registeredUser);
        this.registeredUser = registeredUser;
    }
}
