package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Room;

import java.util.List;

public interface RoomInterface {

    Room addRoom(Room room);

    public List<Room> getAllRooms();

    void deleteRoomById(Integer idRoom);
}
