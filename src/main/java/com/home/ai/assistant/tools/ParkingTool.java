package com.home.ai.assistant.tools;

import com.home.ai.assistant.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Call;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class ParkingTool {

    private static final Logger log = LoggerFactory.getLogger(ParkingTool.class);
    private final TwilioConfig twilio;

    public ParkingTool(TwilioConfig twilio) {
        this.twilio = twilio;
    }

    @Tool(name = "openGate", description = """
            open the gate when I'm getting home or for my visitors when they come over or leave
            """)
    String openGate() {
        log.info("Attempt to open the gate");
        return twilio.initCall();
    }

}
