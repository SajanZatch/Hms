package com.hms.Service;

import com.hms.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }
//sms service
    public String sendSms(String to, String body) {
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber(twilioConfig.getPhoneNumber()),
                body
        ).create();

        return message.getSid();
    }
    //whatsApp service
    public String sendMessage(String to, String body) {
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + to),
                new com.twilio.type.PhoneNumber(twilioConfig.getWhatsappFrom()),
                body
        ).create();
        return message.getSid();
    }

}
