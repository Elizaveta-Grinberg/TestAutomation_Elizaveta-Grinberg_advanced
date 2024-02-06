package ru.levelup.at.advance.homework1.web;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dev.failsafe.internal.util.Assert;
import java.util.Random;

public class SelectCustomerPage {
    private ElementsCollection selectCustomer() {
        return Selenide.$$x("//*[@id='userSelect']/option[@ng-repeat]");
    }

    public SelenideElement loginButton() {
        return Selenide.$x("//button[@type='submit']");
    }

    public int selectRandomCustomerFromList() {
        selectCustomer().first().shouldBe(visible);
        Assert.notNull(selectCustomer().size(), "Customer list is empty");
        int min = 0;
        int max = selectCustomer().size() - 1;
        Random randomAmount = new Random();
        int value = randomAmount.nextInt(max - min + 1) + min;
        selectCustomer().get(value).click();
        return value;
    }

    void selectCustomerFromList(String name) {
        for (SelenideElement elem : selectCustomer()) {
            if (elem.getText().contains(name)) {
                elem.click();
                break;
            }
        }
    }
}
