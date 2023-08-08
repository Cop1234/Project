package org.itsci.project.service;

import org.itsci.project.model.Room;
import org.itsci.project.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> get_ListRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room get_RoomById(String id) {
        return roomRepository.getReferenceById(id);
    }

    @Override
    public Room add_Room(Map<String, String> map) {
        String id = map.get("id");
        String roomName = map.get("roomName");
        String building = map.get("building");
        String latitude = map.get("latitude");
        String longitude = map.get("longitude");

        Long idConvert = Long.parseLong(id);
        Double latitudeConvert = Double.parseDouble(latitude);
        Double longitudeConvert = Double.parseDouble(longitude);

        Room room = new Room(idConvert,roomName,building,latitudeConvert,longitudeConvert);

        return roomRepository.save(room);
    }

    @Override
    public Room update_Room(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void delet_Room(String id) {
        Room Id = roomRepository.getReferenceById(id);

        roomRepository.delete(Id);
        roomRepository.findAll();
    }

    @Override
    public List<Room> getMembersByRoomNameContainingIgnoreCase(String roomName) {
        return roomRepository.getMembersByRoomNameContainingIgnoreCase(roomName);
    }
}
