package ru.levelup.at.advance.homework1.web;

import static com.codeborne.selenide.Condition.visible;
import static ru.levelup.at.advance.homework1.configweb.Currency.DOLLAR;

import io.qameta.allure.Step;
import junit.framework.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.levelup.at.advance.homework1.configweb.Currency;

@DisplayName("Web тест")
public class WithdrawingTest extends AbstractBaseTest {

    @Test
    @DisplayName("Тест на снятие суммы со счета")
    void withdrawingAmountTest() {
        var loginPage = new LoginPage();
        loginPage.login(LoginPage.Login.Customer);
        selectCustomer();
        selectCurrency(DOLLAR);
        withdrawl();
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

    @Step("Снятие большей суммы, чем доступно на счёте клиента")
    void withdrawl() {
        var withdrawling = new CustomerPage();
        withdrawling.withdrawlButton().click();
        String amount = withdrawling.amountGeneration();
        withdrawling.amountSet().setValue(amount);
        withdrawling.buttonSubmitProcess().click();
        Assert.assertTrue("Проверка сообщения об ошибке",
                withdrawling.withdrawlMessage().getText().startsWith("Transaction Failed."));
    }
}
