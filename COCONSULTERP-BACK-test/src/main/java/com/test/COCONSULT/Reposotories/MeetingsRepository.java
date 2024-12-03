package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Meetings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingsRepository extends JpaRepository<Meetings,Integer> {
    Meetings findMeetingsByIdMeeting(Integer idMeeting);

    void deleteMeetingsByIdMeeting(Integer idMeeting);
}
