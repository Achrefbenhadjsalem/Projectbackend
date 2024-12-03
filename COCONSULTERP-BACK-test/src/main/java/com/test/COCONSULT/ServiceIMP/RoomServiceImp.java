package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Room;
import com.test.COCONSULT.Interfaces.RoomInterface;
import com.test.COCONSULT.Reposotories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImp implements RoomInterface {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public void deleteRoomById(Integer idRoom) {
        Room room=roomRepository.findRoomByIdRoom(idRoom);
        roomRepository.deleteById(idRoom);
    }
}
