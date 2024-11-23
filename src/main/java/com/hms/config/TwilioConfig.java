package com.hms.config;


import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

//    @Bean
//    public void init(){
//        Twilio.init("AC1c032321887cac5905f99a4367f5a17e","02af67f5fe95752c5bd088836ac85676");
//    }
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String phoneNumber;

    @Value("${twilio.whatsapp.from}")
    private String whatsappFrom;

    public String getAccountSid() {
        return accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getWhatsappFrom() {
        return whatsappFrom;
    }
}
