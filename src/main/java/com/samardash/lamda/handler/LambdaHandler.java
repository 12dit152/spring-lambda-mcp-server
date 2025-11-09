package com.samardash.lamda.handler;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.samardash.lamda.Application;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@Slf4j
public class LambdaHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {

    public static final String TRACE_ID = "traceId";
    private static final SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(Application.class);
        } catch (ContainerInitializationException ex) {
            throw new RuntimeException("Unable to load spring boot application", ex);
        }
    }

    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest input, Context context) {
        // Set AWS request ID in MDC for correlation with AWS logs
        MDC.put(TRACE_ID, context.getAwsRequestId());
        log.info("User Agent -> {}", getUserAgent(input));
        return handler.proxy(input, context);
    }

    private String getUserAgent(AwsProxyRequest input) {
        // Get User-Agent header
        if (input.getMultiValueHeaders() != null) {
            return input.getMultiValueHeaders().getFirst("User-Agent");
        }

        // Fallback if multi-value headers are not used
        else if (input.getHeaders() != null) {
            return input.getHeaders().get("User-Agent");
        }
        return null;
    }
}