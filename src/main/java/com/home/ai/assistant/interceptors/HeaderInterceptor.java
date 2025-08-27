package com.home.ai.assistant.interceptors;

import io.grpc.*;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.stereotype.Component;

@Component
@GrpcGlobalServerInterceptor
public class HeaderInterceptor implements ServerInterceptor {

    public static final Context.Key<String> X_PHONE_NUMBER = Context.key("X_PHONE_NUMBER");
    public static final Context.Key<String> X_JARVIS_SECRET = Context.key("X_JARVIS_SECRET");

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {

        String xPhoneNumber = metadata.get(Metadata.Key.of("x-phone-number", Metadata.ASCII_STRING_MARSHALLER));
        String xJarvisSecret = metadata.get(Metadata.Key.of("x-jarvis-secret", Metadata.ASCII_STRING_MARSHALLER));

        Context context = Context.current()
                .withValue(X_PHONE_NUMBER, xPhoneNumber)
                .withValue(X_JARVIS_SECRET, xJarvisSecret);


        return Contexts.interceptCall(context, serverCall, metadata, serverCallHandler);
    }
}
