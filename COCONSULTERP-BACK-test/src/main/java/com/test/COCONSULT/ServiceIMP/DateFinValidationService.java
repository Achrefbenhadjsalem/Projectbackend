package com.test.COCONSULT.ServiceIMP;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateFinValidationService {

    public boolean isDateFinSuperieure(LocalDate dateDebut, LocalDate dateFin) {
        return dateDebut != null && dateFin != null && dateFin.isAfter(dateDebut);
    }
}
