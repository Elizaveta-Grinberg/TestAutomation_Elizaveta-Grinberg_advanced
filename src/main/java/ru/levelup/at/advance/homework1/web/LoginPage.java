package ru.levelup.at.advance.homework1.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class LoginPage {
    public LoginPage() {
    }

    private SelenideElement customerLoginButton() {
        return Selenide.$x("//button[@ng-click='customer()']");
    }

    private SelenideElement bankManagerLoginButton() {
        return Selenide.$x("//button[@ng-click='manager()']");
    }

    @Step("Вход в аккаунт")
    void login(Login name) {
        if (name.getName().equals(customerLoginButton().getText())) {
            customerLoginButton().click();
        } else {
            bankManagerLoginButton().click();
        }
    }

    @RequiredArgsConstructor
    @Getter
    public enum Login {
        Customer("Customer Login"),
        Bank_Manager("Bank Manager Login");

        final String name;
    }
}

