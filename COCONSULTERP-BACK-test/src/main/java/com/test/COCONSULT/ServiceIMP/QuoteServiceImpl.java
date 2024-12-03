package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Assignements;
//import com.test.COCONSULT.Entity.Meetings;
import com.test.COCONSULT.Entity.Projets;
import com.test.COCONSULT.Entity.Quote;
import com.test.COCONSULT.Interfaces.QuoteService;
import com.test.COCONSULT.Reposotories.AssignementsRepository;
import com.test.COCONSULT.Reposotories.ExpansesRepository;
//import com.test.COCONSULT.Reposotories.MeetingsRepository;
import com.test.COCONSULT.Reposotories.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    QuoteRepository quoteRepository;

    private ExpansesRepository expansesRepository;
    private AssignementsRepository assignementsRepository;

    @Override
    public List<Quote> retrieveQuotes() {
        return quoteRepository.findAll();
    }

    @Override
    public Projets retrieveQuote(Long idQuote) {
        return Objects.requireNonNull(quoteRepository.findById(idQuote).orElse(null)).getProjets();
    }

    @Override
    public Quote ajouterQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public Quote updateQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public void removeQuote(Long idQuote) {
        quoteRepository.deleteById(idQuote);
    }
    @Override
    public ResponseEntity<Void> validateQuote(Long id, boolean isValid) {
        Quote quote = quoteRepository.findById(id).orElse(null);
        if (quote != null) {
            // Mettez en œuvre la logique de validation ici
            quote.setValid(isValid); // Mettez à jour le statut de validation de la citation
            quoteRepository.save(quote); // Enregistrez la citation mise à jour dans la base de données
            return ResponseEntity.ok().build();
        } else {
            // Si la citation n'est pas trouvée, retournez une erreur 404
            return ResponseEntity.notFound().build();
        }
    }
   @Override
   public List<Quote> getQuotesByValidationAndYear(boolean isValid, int year) {
       return quoteRepository.findByIsValidAndCreationDateYear(isValid, year);
   }
}
