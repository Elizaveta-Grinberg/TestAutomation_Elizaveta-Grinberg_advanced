package ru.levelup.at.advance.homework1.api.util.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor*/
/*@Getter
@AllArgsConstructor
public class UserData {
    String firstname;
    String lastname;
    @JsonProperty("totalprice")
    Number totalPrice;
    @JsonProperty("depositpaid")
    Boolean depositPaid;
    @JsonProperty("bookingdates")
    BookingDates bookingDates;
    @JsonProperty("additionalneeds")
    String additionalNeeds;

}*/


public record UserData(
        String firstname,
        String lastname,
        @JsonProperty("totalprice")
        Number totalPrice,
        @JsonProperty("depositpaid")
        Boolean depositPaid,
        @JsonProperty("bookingdates")
        BookingDates bookingDates,
        @JsonProperty("additionalneeds")
        String additionalNeeds
) {

}

