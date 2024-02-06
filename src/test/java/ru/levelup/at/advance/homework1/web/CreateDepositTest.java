package ru.levelup.at.advance.homework1.web;

import static com.codeborne.selenide.Condition.visible;
import static ru.levelup.at.advance.homework1.configweb.Currency.RUPEE;

import io.qameta.allure.Step;
import junit.framework.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.levelup.at.advance.homework1.configweb.Currency;

@DisplayName("Web тесты")
public class CreateDepositTest extends AbstractBaseTest {

    @Test
    @DisplayName("Тест на создание депозита")
    void testCreateDeposit() {
        var loginPage = new LoginPage();
        loginPage.login(LoginPage.Login.Customer);
        selectCustomer();
        selectCurrency(RUPEE);
        String amount = setDeposit();
        String submitTime = getCurrentTime();
        transaction(amount, submitTime);
    }

    @Step("Выбор клиента")
    void selectCustomer() {
        var customer = new SelectCustomerPage();
        customer.selectRandomCustomerFromList();
        customer.loginButton().shouldBe(visible).click();
    }

    @Step("Выбор валюты")
    void selectCurrency(Currency currency) {
        var currencySelect = new CustomerPage();
        currencySelect.selectAccountNumber(currency);
    }

    @Step("Внести депозит на счёт в случайном размере")
    String setDeposit() {
        var deposit = new CustomerPage();
        deposit.depositButton().click();
        String amount = deposit.amountGeneration();
        deposit.amountSet().setValue(amount);
        deposit.buttonSubmitProcess().click();
        Assert.assertEquals("Проверка суммы депозита", deposit.balance().getText(), amount);
        return amount;
    }

    @Step("Сохранение даты и времени пополнения")
    String getCurrentTime() {
        var currentTime = new TransactionPage();
        String submitTime = currentTime.getCurrentTime();
        var deposit = new CustomerPage();
        deposit.transactionButton().click();
        return submitTime;
    }

    @Step("Проверка транзакции в списке транзакций")
    void transaction(String amount, String time) {
        var verification = new TransactionPage();
        String value = verification.transactionElementsTable().get(0).shouldBe(visible).getText();
        Assert.assertEquals("Проверка даты и времени пополнения", time, value);
        String actualAmount = verification.transactionElementsTable().get(1).getText();
        Assert.assertEquals("Проверка суммы пополнения", amount, actualAmount);
    }
}
