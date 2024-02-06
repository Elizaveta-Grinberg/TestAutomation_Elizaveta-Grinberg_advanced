package ru.levelup.at.advance.homework1.configweb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Currency {
    DOLLAR("Dollar"),
    POUND("Pound"),
    RUPEE("Rupee");

    final String name;

}
