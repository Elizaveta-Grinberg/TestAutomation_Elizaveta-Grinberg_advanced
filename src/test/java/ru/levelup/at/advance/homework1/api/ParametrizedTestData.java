package ru.levelup.at.advance.homework1.api;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static ru.levelup.at.advance.homework1.api.util.DateUtil.getDateAfterNow;
import static ru.levelup.at.advance.homework1.api.util.DateUtil.getDateBeforeNow;
import static ru.levelup.at.advance.homework1.api.util.UserDataGenerator.booking;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import ru.levelup.at.advance.homework1.api.util.object.BookingDates;
import ru.levelup.at.advance.homework1.api.util.object.UserData;

public class ParametrizedTestData {
    public static Stream<Arguments> validBookingData() {
        return Stream.of(
                arguments(
                        "Happy Path",
                        booking()
                ),
                arguments(
                        "Проверка на значение в виде пустой строки для значений с типом String",
                        new UserData("", "", booking().totalPrice(), booking().depositPaid(),
                                new BookingDates(getDateAfterNow(4), getDateAfterNow(7)), "")
                ),
                arguments(
                        "Даты заезда и выезда бронирования позже текущей даты",
                        new UserData(booking().firstname(), booking().lastname(),
                                booking().totalPrice(), booking().depositPaid(),
                                new BookingDates(getDateAfterNow(7), getDateAfterNow(15)), booking().additionalNeeds())
                )
        );
    }

    public static Stream<Arguments> invalidBookingData() {
        return Stream.of(
                arguments(
                        "Проверка заполнения полей null значением",
                        new UserData(null, null, null, null,
                                new BookingDates(null, null), null)
                ),
                arguments(
                        "Дата начала букинга раньше текущей даты",
                        new UserData(booking().firstname(), booking().lastname(), 500, booking().depositPaid(),
                                new BookingDates(getDateBeforeNow(8), getDateAfterNow(10)), booking().additionalNeeds())
                ),
                arguments(
                        "Дата окончания букинга раньше текущей даты",
                        new UserData(booking().firstname(), booking().lastname(),
                                booking().totalPrice(), booking().depositPaid(),
                                new BookingDates(getDateAfterNow(8), getDateBeforeNow(15)), booking().additionalNeeds())
                ),
                arguments(
                        "Дата окончания букинга раньше даты его начала",
                        new UserData(booking().firstname(), booking().lastname(), 444, true,
                                new BookingDates(getDateAfterNow(12), getDateAfterNow(4)), booking().additionalNeeds())
                )
        );
    }
}
