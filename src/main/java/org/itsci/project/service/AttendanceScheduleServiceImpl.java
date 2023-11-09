package org.itsci.project.service;

import lombok.Data;
import org.itsci.project.model.AttendanceSchedule;
import org.itsci.project.model.Registration;
import org.itsci.project.repository.AttendanceScheduleRepository;
import org.itsci.project.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AttendanceScheduleServiceImpl implements AttendanceScheduleService{

    @Autowired
    private AttendanceScheduleRepository attendanceScheduleRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public List<AttendanceSchedule> get_ListAttendanceSchedule() {
        return attendanceScheduleRepository.findAll();
    }

    @Override
    public List<AttendanceSchedule> get_ListAttendanceScheduleByRegistrationId(Long registrationid) {
        List<AttendanceSchedule> attendanceSchedules = attendanceScheduleRepository.findByRegistrationIdOrderByWeekNo(registrationid);
        return attendanceSchedules;
    }

    @Override
    public AttendanceSchedule get_AttendanceScheduleCheckScan(Long registrationid, String week) {
        int weekNo = Integer.parseInt(week);
        return attendanceScheduleRepository.findByRegistrationIdAndWeekNo(registrationid,weekNo);
    }

    @Override
    public AttendanceSchedule get_AttendanceScheduleById(Long id) {
        return attendanceScheduleRepository.getReferenceById(id);
    }

    @Override
    public List<AttendanceSchedule> get_AttendanceScheduleByWeek(String week, String secid) {
        System.out.println("Week: " + week + " Section ID: " + secid);
        int weekNo = Integer.parseInt(week);
        Long sectionId = Long.parseLong(secid);
        return attendanceScheduleRepository.findByWeekNoAndRegistration_Section_IdOrderByRegistration_User_Id(weekNo, sectionId);
    }

    @Override
    public List<AttendanceSchedule> get_AttendanceStudent(String week, String secid,String userID) {
        System.out.println("Week: " + week + " Section ID: " + secid+ " iduser: " + userID);
        int weekNo = Integer.parseInt(week);
        Long sectionId = Long.parseLong(secid);
        Long idUser = Long.parseLong(userID);
      return attendanceScheduleRepository.findByWeekNoAndRegistration_Section_IdAndRegistration_User_IdOrderByRegistration_User_Id(weekNo,sectionId,idUser);
    }

    @Override
    public AttendanceSchedule add_AttendanceSchedule(Map<String, String> map) throws ParseException {
        String regId = map.get("regId");
        String weekNo = map.get("weekNo");
        String checkInTime = map.get("checkInTime");
        String status = map.get("status");
        int weekNoInt = Integer.parseInt(weekNo);
        long regIdLong = Long.parseLong(regId);
        DateFormat Day = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date checkInTimeNow = Day.parse(checkInTime);

        Registration registration = registrationRepository.findById(regIdLong).orElse(null);

        if (registration == null){
            throw new IllegalArgumentException("Subject or User not found");
        }

        AttendanceSchedule attendanceSchedule = new AttendanceSchedule();
        attendanceSchedule.setRegistration(registration);
        attendanceSchedule.setWeekNo(weekNoInt);
        attendanceSchedule.setCheckInTime(checkInTimeNow);
        attendanceSchedule.setStatus(status);

        return attendanceScheduleRepository.save(attendanceSchedule);
    }

    @Override
    public AttendanceSchedule update_AttendanceSchedule(Map<String, String> map) throws ParseException {
        String id = map.get("id");
        String regId = map.get("regId");
        String weekNo = map.get("weekNo");
        String checkInTime = map.get("checkInTime");
        String status = map.get("status");
        int weekNoInt = Integer.parseInt(weekNo);
        long regIdLong = Long.parseLong(regId);
        long Id = Long.parseLong(id);
        DateFormat Day = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date checkInTimeNow = Day.parse(checkInTime);

        Registration registration = registrationRepository.findById(regIdLong).orElse(null);

        if (registration == null){
            throw new IllegalArgumentException("Subject or User not found");
        }

        AttendanceSchedule attendanceSchedule = attendanceScheduleRepository.getReferenceById(Id);
        attendanceSchedule.setRegistration(registration);
        attendanceSchedule.setWeekNo(weekNoInt);
        attendanceSchedule.setCheckInTime(checkInTimeNow);
        attendanceSchedule.setStatus(status);

        return attendanceScheduleRepository.save(attendanceSchedule);
    }

    @Override
    public void delet_AttendanceSchedule(Long id) {
        AttendanceSchedule Id = attendanceScheduleRepository.getReferenceById(id);
        attendanceScheduleRepository.delete(Id);
        attendanceScheduleRepository.findAll();
    }

    @Override
    public AttendanceSchedule update_AttendanceStatus(Map<String, String> map) {
        String attenid = map.get("id");
        String status = map.get("status");

        Optional<AttendanceSchedule> attendanceScheduleOptional = attendanceScheduleRepository.findById(Long.parseLong(attenid));
        AttendanceSchedule attendanceSchedule = attendanceScheduleOptional.get();
        attendanceSchedule.setStatus(status);
        // บันทึกการเปลี่ยนแปลงและคืนค่า AttendanceSchedule ที่อัพเดตแล้ว
        return attendanceScheduleRepository.save(attendanceSchedule);

    }
}
