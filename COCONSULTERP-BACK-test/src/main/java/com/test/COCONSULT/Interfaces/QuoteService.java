package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Assignements;
//import com.test.COCONSULT.Entity.Meetings;
import com.test.COCONSULT.Entity.Projets;
import com.test.COCONSULT.Entity.Quote;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuoteService {
    List<Quote> retrieveQuotes();

    Projets retrieveQuote(Long id);

    Quote ajouterQuote(Quote quote);

    Quote updateQuote(Quote quote);

    void removeQuote(Long id);

    ResponseEntity<Void> validateQuote(Long id, boolean isValid);

    List<Quote> getQuotesByValidationAndYear(boolean isValid, int year);

}