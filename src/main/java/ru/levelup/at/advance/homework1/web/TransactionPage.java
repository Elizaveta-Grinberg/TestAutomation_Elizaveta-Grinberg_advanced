package ru.levelup.at.advance.homework1.web;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TransactionPage {

    public ElementsCollection transactionElementsTable() {
        return Selenide.$$x("//*[@id='anchor0']/td");
    }


    public String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy h:mm:ss a", Locale.ENGLISH);

        Date date = new Date();

        return formatter.format(date);

    }

    public void dateTimeVerification(String time) {
        String value = transactionElementsTable().get(0).getValue();

    }

}
