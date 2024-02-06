package ru.levelup.at.advance.homework1.api.util;

import com.github.javafaker.Faker;
import java.util.Random;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.levelup.at.advance.homework1.api.util.object.BookingDates;
import ru.levelup.at.advance.homework1.api.util.object.UserData;

@Getter
@RequiredArgsConstructor
public class UserDataGenerator {

    static String firstname = new Faker().name().firstName();
    static String lastname = new Faker().name().lastName();
    static Number totalPrice = new Faker().number().numberBetween(1, 500);
    static Boolean depositPaid = new Random().nextBoolean();
    static String additionalNeeds = new Faker().beer().name();
    static String checkIn = DateUtil.getDateAfterNow(generateRandomDate(1, 7));
    static String checkOut = DateUtil.getDateAfterNow(generateRandomDate2(10, 30));

    public static int generateRandomDate(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public static int generateRandomDate2(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static UserData booking() {
        return new UserData(
                firstname,
                lastname,
                totalPrice,
                depositPaid,
                new BookingDates(checkIn, checkOut),
                additionalNeeds
        );
    }
}
