package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.TimeRecord;

import java.util.List;
import java.util.Map;

public interface TimeRecordService {
    List<TimeRecord> retrieveTimeRec();
    TimeRecord retrieveTimeRecord(Long idTimeRec);
    TimeRecord ajouterTimeRec(TimeRecord timeRecord);
    TimeRecord updateTimeRec(TimeRecord timeRecord);
    void removeTimeRec(Long idTimeRec);
    List<TimeRecord> getTimeRecordsForProject(Long projectId);
    Double getTotalTimeForProject(Long projectId);
    List<TimeRecord> retrieveTimeRecByCriteria(Map<String, String> criteria);
    void assignTimeRecordToProject(Long timeRecordId, Long projectId);

    }