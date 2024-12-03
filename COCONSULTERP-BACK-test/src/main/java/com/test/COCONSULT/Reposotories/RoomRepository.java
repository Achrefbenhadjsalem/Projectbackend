package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoomRepository extends JpaRepository<Room,Integer> {
    Room findRoomByIdRoom(Integer idRoom);
}
