package org.itsci.project.service;

import org.itsci.project.model.Course;
import org.itsci.project.model.Room;
import org.itsci.project.model.Section;
import org.itsci.project.model.User;
import org.itsci.project.repository.CourseRepository;
import org.itsci.project.repository.RoomRepository;
import org.itsci.project.repository.SectionRepository;
import org.itsci.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SectionServiceImpl implements SectionService{

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Section> get_ListSection() {
        return sectionRepository.findAll();
    }

    @Override
    public Section get_SectionById(String id) {
        return sectionRepository.getReferenceById(id);
    }

    @Override
    public Section add_Section(Map<String, String> map) {
        String startTime = map.get("startTime");
        String duration = map.get("duration");
        String sectionNumber = map.get("sectionNumber");
        String type = map.get("type");
        String userId = map.get("userId");
        String courseId = map.get("courseId");
        String roomId = map.get("roomId");
        int durationInt = Integer.parseInt(duration);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTimeParse = LocalTime.parse(startTime, formatter);

        Course course = courseRepository.findById(courseId).orElse(null);
        Room room = roomRepository.findById(roomId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (room == null || user == null || course == null) {
            // จัดการกรณีที่ไม่พบ Subject หรือ User
            // คุณสามารถจัดการตามความเหมาะสม เช่น โยนข้อผิดพลาดหรือทำอื่น ๆ ตามที่คุณต้องการ
            throw new IllegalArgumentException("Subject or User not found");
        }

        Section section = new Section();
        section.setStartTime(startTimeParse);
        section.setDuration(durationInt);
        section.setSectionNumber(sectionNumber);
        section.setType(type);
        section.setUser(user);
        section.setCourse(course);
        section.setRoom(room);

        return sectionRepository.save(section);
    }

    @Override
    public Section update_Section(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void delet_Section(String id) {
        Section Id = sectionRepository.getReferenceById(id);
        sectionRepository.delete(Id);
        sectionRepository.findAll();
    }
}
