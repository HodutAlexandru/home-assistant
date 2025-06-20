package com.home.ai.assistant.config;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JarvisConfig {

    @Bean
    McpSyncClient mcpSyncClient() {
        var mcp = McpClient
                .sync(new HttpClientSseClientTransport("https://parking-butler.onrender.com"))
                .build();

        mcp.initialize();
        return mcp;
    }

    @Bean
    ChatClient chatClient(ChatClient.Builder builder, McpSyncClient mcpSyncClient) {

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
                """;

        return builder
                .defaultSystem(system)
                .defaultTools(new SyncMcpToolCallbackProvider(mcpSyncClient))
                .build();

    }

}
