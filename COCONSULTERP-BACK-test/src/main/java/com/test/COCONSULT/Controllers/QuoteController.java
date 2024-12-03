package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Quote;
import com.test.COCONSULT.Interfaces.AssignementsService;
import com.test.COCONSULT.Interfaces.ExpansesService;
import com.test.COCONSULT.Interfaces.ProjetsService;
import com.test.COCONSULT.Interfaces.QuoteService;
import com.test.COCONSULT.Services.SMSService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
@AllArgsConstructor
@CrossOrigin
public class QuoteController {

    private QuoteService quoteService;

    private ProjetsService projetsService;
    private ExpansesService expansesService;
    private AssignementsService assignementsService;
    private SMSService smsService;

    @GetMapping("getAllQuotes")
    public ResponseEntity<List<Quote>> retrieveQuotes() {
        List<Quote> quotes = quoteService.retrieveQuotes();
        return ResponseEntity.ok(quotes);
    }
 @PostMapping("/ajouterQuote")
 public ResponseEntity<Quote> addQuote(@RequestBody Quote quote) {
     Quote savedQuote = quoteService.ajouterQuote(quote);

     String message = " Your Quote expire " + quote.getExpireDate();
     smsService.sendSms("+12512902845", message);

     return new ResponseEntity<>(savedQuote, HttpStatus.CREATED);
 }
  @PutMapping("/validateQuote/{id}")
  public ResponseEntity<Void> validateQuote(@PathVariable("id") Long id, @RequestParam("isValid") boolean isValid) {
      ResponseEntity<Void> responseEntity = quoteService.validateQuote(id, isValid);
      HttpStatus httpStatus = responseEntity.getStatusCode();
      if (httpStatus.is2xxSuccessful()) {
          return ResponseEntity.ok().build();
      } else {
          return ResponseEntity.status(httpStatus).build();
      }
  }
    @PutMapping("/updateQuote/{idQuote}")
    public ResponseEntity<Quote> updateQuote(@PathVariable("idQuote") Long idQuote, @RequestBody Quote quote) {
        quote.setIdQuote(idQuote); // Set the ID from the path variable
        Quote updatedQuote = quoteService.updateQuote(quote);
        if (updatedQuote != null) {
            return ResponseEntity.ok(updatedQuote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteQuote/{idQuote}")
    public ResponseEntity<Void> removeQuote(@PathVariable("idQuote") Long idQuote) {
        quoteService.removeQuote(idQuote);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/quotes")
    public List<Quote> getQuotesByValidationAndYear(@RequestParam boolean isValid, @RequestParam int year) {
        return quoteService.getQuotesByValidationAndYear(isValid, year);
    }
}
