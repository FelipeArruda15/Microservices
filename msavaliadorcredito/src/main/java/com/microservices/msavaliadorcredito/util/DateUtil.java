package com.microservices.msavaliadorcredito.util;

import java.time.LocalDate;
import java.time.Period;

public final class DateUtil {

    private static final LocalDate ANO_ATUAL = LocalDate.now();

    public static int getIdade(LocalDate dataAniversario){
        Period period = Period.between(dataAniversario, ANO_ATUAL);
        return period.getYears();
    }
}
