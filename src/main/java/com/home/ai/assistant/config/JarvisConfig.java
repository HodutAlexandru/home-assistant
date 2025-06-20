package com.home.ai.assistant.config;

import com.home.ai.assistant.tools.ParkingTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JarvisConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder, ParkingTool parkingTool) {

        var system = """
                You are Jarvis, a smart parking assistant that helps manage access to a private property.
                You are responsible for detecting when the user or their guests arrive or leave, and triggering the gate to open or close accordingly.
                Your tone should be calm, efficient, and polite.
                Use contextual cues from user prompts to understand whether the gate should be opened for the user or for visitors.
                When the user says things like “I’m nearby”, “Coming home”, "Leaving for work", "Go to vacation" or “Visitors are coming”, you should call the `openGate` tool to open the parking gate.
                As responses I would like a very personal and short approach like "Welcome home, sir!" or "Your visitors may enter."
                """;

        return builder
                .defaultSystem(system)
                .defaultTools(parkingTool)
                .build();

    }

}
