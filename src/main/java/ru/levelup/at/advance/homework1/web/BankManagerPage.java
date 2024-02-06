package ru.levelup.at.advance.homework1.web;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Modal;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.levelup.at.advance.homework1.configweb.Currency;

public class BankManagerPage {

    public SelenideElement addCustomerButton() {
        return Selenide.$x("//button[@ng-click='addCust()']");
    }

    public SelenideElement openAccountButton() {
        return Selenide.$x("//button[@ng-click='openAccount()']");
    }

    public SelenideElement customersButton() {
        return Selenide.$x("//button[@ng-click='showCust()']");
    }

    private ElementsCollection addCustomerTab() {
        return Selenide.$$x("//form[@ng-submit='addCustomer()']/div/input");
    }

    private ElementsCollection customerList() {
        return Selenide.$$x("//select[@id='userSelect']/option");
    }

    private ElementsCollection currencyList() {
        return Selenide.$$x("//select[@id='currency']/option");
    }

    public SelenideElement searchCustomerInTable() {
        return Selenide.$x("//input[@placeholder='Search Customer']");
    }

    public SelenideElement accountNumber() {
        return Selenide.$x("//span[@ng-repeat='account in cust.accountNo']");
    }

    public SelenideElement submitProcessButton() {
        return Selenide.$x("//button[@type='submit']");
    }

    public SelenideElement homeButton() {
        return Selenide.$x("//button[@class='btn home']");
    }

    void addCustomerTabs(CustomerInfo setData, String name) {
        for (SelenideElement elem : addCustomerTab()) {
            if (elem.getAttribute("placeholder").equals(setData.getName())) {
                elem.click();
                elem.setValue(name);
                break;
            }
        }
    }

    void modalWindowAccept() {
        Modal a = new Modal(Selenide.webdriver().driver());
        a.confirm();
    }

    void selectAddedCustomer(String name) {
        for (SelenideElement elem : customerList()) {
            if (elem.getText().contains(name)) {
                elem.click();
                break;
            }
        }
    }

    void selectCurrency(Currency currency) {
        for (SelenideElement elem : currencyList()) {
            if (elem.getText().contains(currency.getName())) {
                elem.click();
                break;
            }
        }
    }

    @RequiredArgsConstructor
    public enum CustomerInfo {
        FIRST_NAME("First Name"),
        LAST_NAME("Last Name"),
        POST_CODE("Post Code");

        @Getter
        final String name;

    }
}
