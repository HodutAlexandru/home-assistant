package com.home.ai.assistant.service;

import com.home.ai.assistant.controller.JarvisChat;
import io.grpc.Context;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;

import static com.home.ai.assistant.interceptors.HeaderInterceptor.X_JARVIS_SECRET;
import static com.home.ai.assistant.interceptors.HeaderInterceptor.X_PHONE_NUMBER;

@GrpcService
public class AiServiceImpl extends AiServiceGrpc.AiServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(AiServiceImpl.class);
    private final ChatClient ai;

    public AiServiceImpl(ChatClient ai) {
        this.ai = ai;
    }

    @Override
    public void submitRequest(Chore chore, StreamObserver<AiResponse> observer) {
        final String xPhoneNumber = X_PHONE_NUMBER.get();
        final String xJarvisSecret = X_JARVIS_SECRET.get();

        if (xPhoneNumber != null
                && xPhoneNumber.equals("+40723851117")
                && xJarvisSecret != null
                && xJarvisSecret.equals("jarvisSecret")) {
            log.info("Successfully authenticated!");

            String aiResponse = this.ai
                    .prompt()
                    .user(chore.getPrompt())
                    .call()
                    .content();

            AiResponse response = AiResponse.newBuilder()
                    .setResponse("Response from claude: " + aiResponse)
                    .build();

            observer.onNext(response);
            observer.onCompleted();
        }
    }

}
