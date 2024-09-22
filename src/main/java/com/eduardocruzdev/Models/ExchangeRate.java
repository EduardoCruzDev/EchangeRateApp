package com.eduardocruzdev.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor


public class ExchangeRate {
    private String base_code;
    private String target_code;
    @Getter
    private double conversion_rate;

    @Override
    public String toString() {
        return "ExchangeRate" +
                "Base Currency= " + base_code + '\'' +
                ",Target Currency= " + target_code + '\'' +
                ",Conversion Rate=" + conversion_rate;
    }

}
