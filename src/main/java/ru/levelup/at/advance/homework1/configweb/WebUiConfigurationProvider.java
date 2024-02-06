package ru.levelup.at.advance.homework1.configweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WebUiConfigurationProvider {
    public static WebUiConfiguration getConfig() {
        final var webUiConfigPath = System.getenv("WEB_UI_CONFIG_PATH");

        if (webUiConfigPath == null) {
            throw new IllegalArgumentException("Please set up 'url' variable to env variables");
        }

        try {
            return new ObjectMapper().readValue(new File(webUiConfigPath), WebUiConfiguration.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
