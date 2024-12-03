package com.test.COCONSULT.ServiceIMP;


import com.test.COCONSULT.Entity.TimeRecord;
import com.test.COCONSULT.Interfaces.TimeRecordService;
import com.test.COCONSULT.Reposotories.TimeRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class TimeRecordServiceImpl implements TimeRecordService {
 private final TimeRecordRepository timeRecordRepository;

   @Override
   public List<TimeRecord> retrieveTimeRec() {
      return timeRecordRepository.findAll();
   }

   @Override
   public TimeRecord updateTimeRec(TimeRecord timeRecord) {
      return timeRecordRepository.save(timeRecord);
   }

   @Override
   public TimeRecord ajouterTimeRec(TimeRecord timeRecord) {
      return timeRecordRepository.save(timeRecord);
   }

   @Override
   public TimeRecord retrieveTimeRecord(Long idTimeRec) {
      return timeRecordRepository.findById(idTimeRec).orElse(null);
   }

   @Override
   public void removeTimeRec(Long idTimeRec) {
      timeRecordRepository.deleteById(idTimeRec);
   }

   @Override
   public List<TimeRecord> getTimeRecordsForProject(Long projectId) {
      return timeRecordRepository.findByProjetsIdProjet(projectId);
   }


   @Override
   public Double getTotalTimeForProject(Long projectId) {
      LocalDate duration = LocalDate.now(); // Or retrieve the duration from somewhere

      long totalTime = timeRecordRepository.countByProjectIdAndDuration(projectId, duration);
      return Double.valueOf(totalTime);
   }

   @Override
   public List<TimeRecord> retrieveTimeRecByCriteria(Map<String, String> criteria) {
      // Initialiser une spécification vide
      Specification<TimeRecord> spec = Specification.where(null);

      // Vérifier chaque critère et construire la spécification en conséquence
      for (Map.Entry<String, String> entry : criteria.entrySet()) {
         String key = entry.getKey();
         String value = entry.getValue();

         // Ajouter une clause de filtrage en fonction du critère
         switch (key) {
            case "date":
               LocalDate date = LocalDate.parse(value); // Supposons que la date soit fournie sous forme de chaîne au format ISO yyyy-MM-dd
               spec = spec.and((root, query, cb) -> cb.equal(root.get("date"), date));
               break;
            case "duration":
               Integer duration = Integer.parseInt(value); // Supposons que la durée soit fournie sous forme de chaîne
               spec = spec.and((root, query, cb) -> cb.equal(root.get("duration"), duration));
               break;
            // Ajouter d'autres cas pour d'autres critères si nécessaire
         }
      }
      List<TimeRecord> filteredTimeRecords = timeRecordRepository.findAll((Sort) spec);

      return filteredTimeRecords;
   }

   @Override
   public void assignTimeRecordToProject(Long timeRecordId, Long projectId) {
      timeRecordRepository.assignTimeRecordToProject(timeRecordId, projectId);
   }
}