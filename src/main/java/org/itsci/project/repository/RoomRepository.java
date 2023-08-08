package org.itsci.project.repository;

import org.itsci.project.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,String> {
    List<Room> getMembersByRoomNameContainingIgnoreCase (String roomName);
}
