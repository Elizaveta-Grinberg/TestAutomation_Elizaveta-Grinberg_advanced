package ru.levelup.at.advance.homework1.web;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import java.util.Random;
import ru.levelup.at.advance.homework1.configweb.Currency;

public class CustomerPage {

    protected ElementsCollection accountSelect() {
        return Selenide.$$x("//*[@id='accountSelect']/option");
    }

    public SelenideElement accountNumber() {
        return Selenide.$x("//div[@class ='center'][1]/strong[1]");
    }

    public SelenideElement balance() {
        return Selenide.$x("//div[@class ='center'][1]/strong[2]");
    }

    public SelenideElement currency() {
        return Selenide.$x("//div[@class ='center'][1]/strong[3]");
    }

    public SelenideElement transactionButton() {
        return Selenide.$x("//div/button[@ng-click='transactions()']");
    }

    public SelenideElement depositButton() {
        return Selenide.$x("//div/button[@ng-click='deposit()']");
    }

    public SelenideElement withdrawlButton() {
        return Selenide.$x("//div/button[@ng-click='withdrawl()']");
    }

    /*private SelenideElement withdrawnTextLabel(){
        return Selenide.$("//label['Amount to be Withdrawn :']");
    }*/
    public SelenideElement amountSet() {
        return Selenide.$x("//*[@type='number']");
    }

    public SelenideElement buttonSubmitProcess() {
        return Selenide.$x("//button[@type='submit']");
    }

    public SelenideElement withdrawlMessage() {
        return Selenide.$x("//span[@class='error ng-binding']");
    }

    public CustomerPage() {
        Selenide.page(this);
    }

    public void selectAccountNumber(Currency name) {
        accountSelect().first().shouldBe(visible);
        for (int b = 0; b < accountSelect().size(); b++) {
            while (!currency().text().equals(name.getName())) {
                accountSelect().get(b).click();
                break;
            }
        }
    }

    public String amountGeneration() {
        int min = 150;
        int max = 2500;
        Random randomAmount = new Random();
        if (buttonSubmitProcess().getText().equals("Withdraw")) {
            String text = balance().getText();
            int integer = Integer.parseInt(text.trim());
            int i = integer + 1;
            return String.valueOf(i);
        } else {
            return String.valueOf(randomAmount.nextInt((max - min) + min));
        }
    }


    /*@RequiredArgsConstructor
    public enum Currency {
        DOLLAR("Dollar"),
        POUND("Pound"),
        RUPEE("Rupee");

        @Getter
        final String name;

    }*/

}
