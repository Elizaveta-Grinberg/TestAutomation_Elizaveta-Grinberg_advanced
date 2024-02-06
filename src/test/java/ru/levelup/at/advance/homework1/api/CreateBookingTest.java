package ru.levelup.at.advance.homework1.api;

import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_OK;

import io.qameta.allure.Allure;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.levelup.at.advance.homework1.api.config.RequestSpecificationConfig;
import ru.levelup.at.advance.homework1.api.config.ResponseSpecificationConfig;
import ru.levelup.at.advance.homework1.api.util.object.UserData;

@DisplayName("API тесты сервиса создания бронирования")
public class CreateBookingTest {
    RequestSpecification requestSpecification = RequestSpecificationConfig.setUpRequest();

    @ParameterizedTest(name = " : {0}")
    @DisplayName("Тест с проверкой валидных данных")
    @MethodSource("ru.levelup.at.advance.homework1.api.ParametrizedTestData#validBookingData")
    public void validBookingTest(String testName, UserData userDataInfo) {
        var response = Allure.step("Отправка запроса", () ->
                        ResponseSpecificationConfig.of(requestSpecification).createBooking(userDataInfo)
                                .then()
                                .log().all()
                                .statusCode(SC_OK));


        Allure.step("Проверка id созданного бронирования", () ->
                response.body("bookingid", Matchers.notNullValue())
        );

        Allure.step("Проверка созданных данных бронирования", () ->
                response.rootPath("booking")
                        .body("firstname", Matchers.equalTo(userDataInfo.firstname()))
                        .body("lastname", Matchers.equalTo(userDataInfo.lastname()))
                        .body("totalprice", Matchers.equalTo(userDataInfo.totalPrice()))
                        .body("depositpaid", Matchers.equalTo(userDataInfo.depositPaid()))
                        .body("bookingdates.checkin", Matchers.equalTo(userDataInfo.bookingDates().checkIn()))
                        .body("bookingdates.checkout", Matchers.equalTo(userDataInfo.bookingDates().checkOut()))
                        .body("additionalneeds", Matchers.equalTo(userDataInfo.additionalNeeds()))
        );


    }

    @ParameterizedTest(name = " : {0}")
    @DisplayName("Тест с проверкой невалидных данных")
    @MethodSource("ru.levelup.at.advance.homework1.api.ParametrizedTestData#invalidBookingData")
    void testCreateInvalidBooking(String testName, UserData userDataInfo) {
        Allure.step("Отправка запроса", () ->
                ResponseSpecificationConfig.of(requestSpecification).createBooking(userDataInfo)
                        .then()
                        .log().all()
                        .statusCode(SC_INTERNAL_SERVER_ERROR));
    }
}
