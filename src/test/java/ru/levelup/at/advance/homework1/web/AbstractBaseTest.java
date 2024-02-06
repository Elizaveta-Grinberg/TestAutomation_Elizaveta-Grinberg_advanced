package ru.levelup.at.advance.homework1.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.levelup.at.advance.homework1.configweb.WebUiConfiguration;
import ru.levelup.at.advance.homework1.configweb.WebUiConfigurationProvider;

public class AbstractBaseTest {

    protected WebUiConfiguration config;

    @BeforeEach
    void setUp() {
        SelenideLogger.addListener("Selenide Listener", new AllureSelenide());
        config = WebUiConfigurationProvider.getConfig();

        Selenide.open(config.url());
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}
