package org.itsci.project.controller;

import org.itsci.project.model.Room;
import org.itsci.project.service.RoomService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    //getListRoom
    @RequestMapping("/list")
    public ResponseEntity get_ListRoom (){
        try {
            List<Room> rooms = roomService.get_ListRoom();
            return new ResponseEntity<>(rooms , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list Room!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/getbyid/{id}")
    public ResponseEntity get_RoomById (@PathVariable("id") String id){
        try {
            Room room = roomService.get_RoomById(id);
            return new ResponseEntity<>(room , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Room by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity add_Room (@RequestBody Map<String,String> map){
        try {
            Room room = roomService.add_Room(map);
            return new ResponseEntity<>(room, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Add Subject!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/update")
    public ResponseEntity update_Room (@RequestBody Room room){
        try {
            Room update_room = roomService.update_Room(room);
            return new ResponseEntity<>(update_room, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Add Subject!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity delet_Room (@PathVariable("id") String id){
        try {
            Room room = roomService.get_RoomById(id);
            String roomName = room.getRoomName();

            roomService.delet_Room(id);

            return new ResponseEntity<>("Room " + roomName + " was delete!", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete Room by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //หาข้อมูลทั้งหมดด้วยตัวอักษร
    @GetMapping("/getbycontname/{roomName}")
    public ResponseEntity getMembersByRoomNameContainingIgnoreCase (@PathVariable("roomName") String roomName){
        try {
            List<Room> rooms = roomService.getMembersByRoomNameContainingIgnoreCase(roomName);
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to getRoom by name", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
