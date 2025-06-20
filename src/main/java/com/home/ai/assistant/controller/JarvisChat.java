package com.home.ai.assistant.controller;

import com.home.ai.assistant.records.Chore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jarvis")
public class JarvisChat {


    private static final Logger log = LoggerFactory.getLogger(JarvisChat.class);
    private final ChatClient ai;

    public JarvisChat(ChatClient ai) {
        this.ai = ai;
    }

    @PostMapping
    public ResponseEntity<String> chat(
            @RequestHeader("x-phone-number") String phoneNumber,
            @RequestHeader("x-jarvis-secret") String jarvisSecret,
            @RequestBody Chore chore) {
        if (phoneNumber.equals("+40723851117") && jarvisSecret.equals("jarvisSecret")) {
            log.info("Successfully authenticated!");

            return ResponseEntity.ok(this.ai
                    .prompt()
                    .user(chore.prompt())
                    .call()
                    .content());
        }

        log.warn("Connection attempt. Consider enhance security!");
        return ResponseEntity.internalServerError().build();
    }

}
