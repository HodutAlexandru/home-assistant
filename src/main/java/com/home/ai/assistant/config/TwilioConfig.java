package com.home.ai.assistant.config;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class TwilioConfig {

    @Value("${twilio.sid}")
    private String sid;

    @Value("${twilio.auth-token}")
    private String token;

    private static final String JARVIS_PHONE_NUMBER = "+40723851117";
    private static final String GATE_PHONE_NUMBER = "+40735365445";
//    private static final String EXERCISE_PHONE_NUMBER = "+40728950804";
//    private static final String GATE_PHONE_NUMBER = "+40723851117";

    public String initCall() {
        Twilio.init(sid, token);

        return Call.creator(
                new PhoneNumber(GATE_PHONE_NUMBER),
                new PhoneNumber(JARVIS_PHONE_NUMBER),
                URI.create("http://demo.twilio.com/docs/voice.xml")
        ).create().getSid();
    }

}
