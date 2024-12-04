package com.example.CRM.utils;

import com.example.CRM.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientUtils {

    private static WebClient.Builder webClientBuilder;

    private static String caseNotFoundMessage = "Case Not Found";

    @Autowired
    private WebClientUtils(WebClient.Builder webClientBuilder) {
        WebClientUtils.webClientBuilder = webClientBuilder;
    }

    public static String externalPostRequest(String url, Object requestBody) {
        return webClientBuilder.build()
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Mono.error(new NotFoundException(caseNotFoundMessage));
                    }
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorBody -> Mono.error(new RuntimeException("Client error: " + errorBody)));
                })
                .bodyToMono(String.class)
                .block();
    }

    public static String externalPutRequest(String url, Object requestBody) {
        return webClientBuilder.build()
                .put()
                .uri(url)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Mono.error(new NotFoundException(caseNotFoundMessage));
                    }
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorBody -> Mono.error(new RuntimeException("Client error: " + errorBody)));
                })
                .bodyToMono(String.class)
                .block();
    }

    public static String externalGetRequest(String url) {
        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Mono.error(new NotFoundException(caseNotFoundMessage));
                    }
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorBody -> Mono.error(new RuntimeException("Client error: " + errorBody)));
                })
                .bodyToMono(String.class)
                .block();
    }

    public static String externalDeleteRequest(String url) {
        return webClientBuilder.build()
                .delete()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Mono.error(new NotFoundException(caseNotFoundMessage));
                    }
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorBody -> Mono.error(new RuntimeException("Client error: " + errorBody)));
                })
                .bodyToMono(String.class)
                .block();
    }


}
