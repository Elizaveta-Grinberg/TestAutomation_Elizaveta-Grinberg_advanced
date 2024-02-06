package ru.levelup.at.advance.homework1.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestSpecificationConfig {
    
    public static RequestSpecification setUpRequest() {
        final String pathName = "API_CONFIG_PATH";
        final var configFilePath = System.getenv(pathName);

        if (configFilePath == null) {
            throw new IllegalArgumentException(
                    String.format("Please set up %s variable to environment variables", pathName));
        }

        ApiConfiguration apiConfiguration;
        try {
            apiConfiguration = new ObjectMapper().readValue(new File(configFilePath), ApiConfiguration.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(apiConfiguration.url())
                //.setBasePath(apiConfiguration.basePath())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .setAccept(ContentType.JSON)
                .build();
    }
}
