package ru.levelup.at.advance.homework1.api.util.object;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookingDates(
        @JsonProperty("checkin")
        String checkIn,
        @JsonProperty("checkout")
        String checkOut

) {

}
