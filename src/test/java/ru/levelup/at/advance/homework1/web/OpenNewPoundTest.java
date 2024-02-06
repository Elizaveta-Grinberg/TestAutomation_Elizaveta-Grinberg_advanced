package ru.levelup.at.advance.homework1.web;

import static com.codeborne.selenide.Condition.visible;
import static ru.levelup.at.advance.homework1.web.BankManagerPage.CustomerInfo.FIRST_NAME;
import static ru.levelup.at.advance.homework1.web.BankManagerPage.CustomerInfo.LAST_NAME;
import static ru.levelup.at.advance.homework1.web.BankManagerPage.CustomerInfo.POST_CODE;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import junit.framework.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.levelup.at.advance.homework1.configweb.Currency;

@DisplayName("Web тест для открытия нового счета")
public class OpenNewPoundTest extends AbstractBaseTest {

    @Test
    @DisplayName("Тест на создание нового счета и клиента")
    void openNewPound() {
        var loginPage = new LoginPage();
        loginPage.login(LoginPage.Login.Bank_Manager);
        String firstName = new Faker().funnyName().name();
        String lastName = new Faker().name().name();
        String postCode = new Faker().code().ean8();
        createCustomer(firstName, lastName, postCode);
        openAccount(firstName);
        final String accountNumber = showAccountNumber(firstName);
        loginPage.login(LoginPage.Login.Customer);
        selectCustomer(firstName);
        var customerPage = new CustomerPage();
        Assert.assertEquals("Номер созданного аккаунта совпадает",
                customerPage.currency().getText(), Currency.POUND.getName());
        Assert.assertEquals("Валюта созданного аккаунта совпадает",
                accountNumber, customerPage.accountNumber().getText());
    }

    @Step("Ввести данные нового клиента")
    void createCustomer(String firstName, String lastName, String postCode) {
        var customer = new BankManagerPage();
        customer.addCustomerButton().click();
        customer.addCustomerTabs(FIRST_NAME, firstName);
        customer.addCustomerTabs(LAST_NAME, lastName);
        customer.addCustomerTabs(POST_CODE, postCode);
        customer.submitProcessButton().click();
        customer.modalWindowAccept();
    }

    @Step("Выбрать созданного клиента и валюту")
    void openAccount(String name) {
        var customer = new BankManagerPage();
        customer.openAccountButton().click();
        customer.selectAddedCustomer(name);
        customer.selectCurrency(Currency.POUND);
        customer.submitProcessButton().click();
        customer.modalWindowAccept();
    }

    @Step("Проверка создания номера счета")
    String showAccountNumber(String name) {
        var customer = new BankManagerPage();
        customer.customersButton().click();
        customer.searchCustomerInTable().shouldBe(visible).setValue(name).click();
        String accountNumber = customer.accountNumber().getText();
        customer.homeButton().click();
        return accountNumber;
    }

    @Step("Выбор определенного клиента")
    void selectCustomer(String name) {
        var customer = new SelectCustomerPage();
        customer.selectCustomerFromList(name);
        customer.loginButton().shouldBe(visible).click();
    }
}
