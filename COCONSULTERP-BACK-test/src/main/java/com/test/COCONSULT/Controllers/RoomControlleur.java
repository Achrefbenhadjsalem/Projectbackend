package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Room;
import com.test.COCONSULT.Interfaces.RoomInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Room")
@CrossOrigin("*")
public class RoomControlleur {
    @Autowired
    RoomInterface roomInterface;
    @GetMapping("/getAllRooms")
    public List<Room> getAllRooms() {
        return roomInterface.getAllRooms();
    }
    @PostMapping("/addRoom")
    public Room addRoom(@RequestBody Room room) {
        return roomInterface.addRoom(room);
    }
    @DeleteMapping("/deleteRoomById/{idRoom}")
    public void deleteRoomById(@PathVariable("idRoom") Integer idRoom) {
        roomInterface.deleteRoomById(idRoom);
    }
}
