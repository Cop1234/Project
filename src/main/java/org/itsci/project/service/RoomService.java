package org.itsci.project.service;

import org.itsci.project.model.Room;

import java.util.List;
import java.util.Map;

public interface RoomService {

    //CRUD 5 KINDS

    //GET ALL
    List<Room> get_ListRoom ();

    //GET BY ID
    Room get_RoomById(String id);

    //CREATE
    Room add_Room(Map<String,String> map);

    //Update
    Room update_Room (Room room);

    //Delete
    void delet_Room(String id);

    //GET BY CONTAINING NAME
    List<Room> getMembersByRoomNameContainingIgnoreCase (String roomName);
}
