package com.home.ai.assistant.tools;

import com.home.ai.assistant.config.TwilioConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class ParkingButler {

    private static final Logger log = LoggerFactory.getLogger(ParkingButler.class);
    private final TwilioConfig twilio;

    public ParkingButler(TwilioConfig twilio) {
        this.twilio = twilio;
    }

    @Tool(name = "openGate", description = """
            Opens the parking gate by initiating a phone call to the gate system. 
            Use this as well when residents or visitors need to enter or exit the parking area.
            """)
    String openGate() {
        log.info("Attempt to open the gate");
        return twilio.initCall();
    }

}
