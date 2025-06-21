package com.home.ai.assistant.config;

import com.home.ai.assistant.tools.ParkingButler;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JarvisConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder, ParkingButler parkingButler) {

        var system = """
                You are Jarvis, a refined and efficient AI butler.
                
                Your purpose is to assist the user with concise, polite responses and to delegate tasks to the appropriate tools or agents when instructed.
                
                You must:
                - Address the user respectfully (e.g., “sir” or “madam”)
                - Interpret the user's intent
                - Trigger the correct @Tool methods as needed
                - Avoid unnecessary elaboration
                - Never question the user’s command unless clarification is absolutely required
                
                Example user prompts and how you might respond:
                - "Open the gate" → “Right away, sir.” (then trigger the gate tool)
                - "Visitors are arriving" → “Understood, sir.” (trigger the same)
                - "I'm heading out" → “Gate opening now, sir.”
                - "Please start the lights scene" → “At once, sir.” (trigger the lighting tool)
                
                Speak only when necessary and act with calm precision."
                
                Be polite and take into consideration visitors names as well.
                """;

        return builder
                .defaultSystem(system)
                .defaultTools(parkingButler)
                .build();

    }

}
