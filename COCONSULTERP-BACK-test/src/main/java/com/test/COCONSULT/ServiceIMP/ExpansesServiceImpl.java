package com.test.COCONSULT.ServiceIMP;


import com.test.COCONSULT.Entity.Expanses;
import com.test.COCONSULT.Interfaces.ExpansesService;
import com.test.COCONSULT.Reposotories.ExpansesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ExpansesServiceImpl implements ExpansesService {


    ExpansesRepository expansesRepository;

    @Override
    public List<Expanses> retrieveExpanses() {
        return expansesRepository.findAll();
    }

    @Override
    public Expanses updateExpanses(Expanses expanses) {
        return expansesRepository.save(expanses);
    }

    @Override
    public Expanses addExpanses(Expanses expanses) {
        return expansesRepository.save(expanses);
    }

    @Override
    public Expanses retrieveExpanses(Long idExpanses) {
        Optional<Expanses> expansesOptional = expansesRepository.findById(idExpanses);
        return expansesOptional.orElse(null);
    }

    @Override
    public void removeExpanses(Long idExpanses) {
        expansesRepository.deleteById(idExpanses);
    }
    @Override
    public List<Expanses> getExpansesForProject(Long projectId) {
        return expansesRepository.findByProjetsIdProjet(projectId);
    }

    @Override
    public List<Expanses> getExpansesUpdatedAfterDate(Long projectId, LocalDate date) {
        return expansesRepository.findByProjetsIdProjetAndDateAfter(projectId, date);
    }

    @Override
    public List<Expanses> getLastExpanses(Long projectId, int limit) {
        return expansesRepository.findTopNByProjetsIdProjetOrderByDateDesc(projectId);
    }
}