package dev.abhi.userservice.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

//token also needs to be persisted in db
@Entity
@Getter
@Setter
public class VerificationToken {

    private static int VALIDITY_TIME = 4*60 ;   //in minutes

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class)
    private User user;

    private Date expiryTime;

    public VerificationToken(){}

    public VerificationToken(User user) {
        String token = generateToken();

        this.token = token;
        this.user = user;

        this.expiryTime = calculateExpiryTime();
    }

    public void updateToken(){
        this.token = generateToken();
        this.expiryTime = calculateExpiryTime();
    }

    //universely unique id
    private String generateToken(){
        return  UUID.randomUUID().toString();
    }

    private Date calculateExpiryTime(){

        Calendar calendar = Calendar.getInstance();

        Date currentDateAndTime = new Date();

        calendar.setTimeInMillis(currentDateAndTime.getTime());

        calendar.add(Calendar.MINUTE, VALIDITY_TIME);

        return calendar.getTime();
    }

}
