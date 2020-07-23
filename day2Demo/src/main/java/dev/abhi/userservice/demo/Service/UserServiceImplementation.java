package dev.abhi.userservice.demo.Service;

import dev.abhi.userservice.demo.Repository.PasswordResetTokenRepository;
import dev.abhi.userservice.demo.Repository.UserRepository;
import dev.abhi.userservice.demo.Repository.VerificationTokenRepository;
import dev.abhi.userservice.demo.dto.UpdatePasswordDto;
import dev.abhi.userservice.demo.dto.userDto;
import dev.abhi.userservice.demo.event.SuccessfullRegistration;
import dev.abhi.userservice.demo.model.User;
import dev.abhi.userservice.demo.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

//service annotation tells spring to make a bean of our class
// TODO: study transactional annotation
@Service
@Transactional
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

//    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    //here we have avoided creating a custom event producer and used the internal spring event publisher
    // TODO: study about publishing an event
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Override
    public User registerUser(userDto userdto) {
        if(userRepository.findByEmail(userdto.getEmail()) != null){
            //TODO: throw exception
        }
        User user = new User();
        user.setFullname(userdto.getFullname());
        user.setEmail(userdto.getEmail());
        user.setActive(false);
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));

        //savedUser will also have an id
        User savedUser = userRepository.save(user);

        applicationEventPublisher.publishEvent(
                new SuccessfullRegistration(savedUser)
        );
        return savedUser;
    }

    @Override
    public User validateUser(String token) {
        // check if token exists
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken == null){
            return null;
        }

        if(verificationToken.getExpiryTime().getTime() > new Date().getTime()){

            //mark user active and save and return user
            User verifiedUser = verificationToken.getUser();
            verifiedUser.setActive(true);
            userRepository.save(verifiedUser);
            verificationTokenRepository.delete(verificationToken);
            return verifiedUser;

        }
        //check if it is not expired
        return null;
    }

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository ;

    @Override
    public User generatePasswordResetToken(userDto userdto) {

        //generate a password reset token which is going to be a verification token

        //save the token in the PassowrdResetTokenRepo

        //fetches the corresponding user from the repository
        User user = userRepository.findByEmail(userdto.getEmail());

        VerificationToken passwordResetToken = new VerificationToken(user);

        passwordResetTokenRepository.save(passwordResetToken);

        //TODO:Send a password reset email with the corresponding token
        return user;
    }

    @Override
    public User validatePasswordResetToken(String token) {

        VerificationToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if(passwordResetToken == null){
            return null;
        }
        // TODO:ask naman about the return type here, should it be user or void ?

        if(passwordResetToken.getExpiryTime().getTime() > new Date().getTime()){
            //TODO:Redirect to the new password reset page
        }
        else{
            //TODO:Display message "expired token"
        }
        return passwordResetToken.getUser();
    }

    @Override
    public User updatePassword(UpdatePasswordDto updatePasswordDto) {

        //fetch the user
        //set the updated password
        //save the user
        //delete the token

        User user = userRepository.findByEmail(updatePasswordDto.getEmail());

        user.setPassword(passwordEncoder.encode(updatePasswordDto.getNewPassword()));

        userRepository.save(user);

        VerificationToken passwordResetToken = passwordResetTokenRepository.findByToken(updatePasswordDto.getToken());

        passwordResetTokenRepository.delete(passwordResetToken);

        return userRepository.findByEmail(updatePasswordDto.getEmail());
    }
}
