package ru.levelup.at.advance.homework1.api.config;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import ru.levelup.at.advance.homework1.api.util.object.UserData;

@RequiredArgsConstructor(staticName = "of")
public class ResponseSpecificationConfig {
    private static final String BOOKING_ENDPOINT = "/booking";
    private final RequestSpecification requestSpecification;

    public Response createBooking(final UserData bookingInfo) {
        return given()
                .spec(requestSpecification)
                .body(bookingInfo)
                .when()
                .post(BOOKING_ENDPOINT)
                .andReturn();
    }
}
