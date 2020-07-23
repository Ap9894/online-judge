package dev.abhi.userservice.demo.event.listner;

import dev.abhi.userservice.demo.Repository.VerificationTokenRepository;
import dev.abhi.userservice.demo.event.SuccessfullRegistration;
import dev.abhi.userservice.demo.model.User;
import dev.abhi.userservice.demo.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SuccessfullRegistrationEventListner implements ApplicationListener<SuccessfullRegistration> {

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Override
    public void onApplicationEvent(SuccessfullRegistration successfullRegistration) {

        User registeredUser = successfullRegistration.getRegisteredUser();

        VerificationToken verificationToken = new VerificationToken(registeredUser);

        verificationTokenRepository.save(verificationToken);

        // TODO:send mail to user having the verification link containing the token in its request parameter



    }
}
