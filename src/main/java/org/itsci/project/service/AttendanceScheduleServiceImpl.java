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
        List<AttendanceSchedule> attendanceSchedules = attendanceScheduleRepository.findByRegistrationId(registrationid);
        return attendanceSchedules;
    }

    @Override
    public AttendanceSchedule get_AttendanceScheduleById(Long id) {
        return attendanceScheduleRepository.getReferenceById(id);
    }

    @Override
    public AttendanceSchedule add_AttendanceSchedule(Map<String, String> map) throws ParseException {
        String regId = map.get("regId");
        String weekNo = map.get("weekNo");
        String checkInTime = map.get("checkInTime");
        String status = map.get("status");
        int weekNoInt = Integer.parseInt(weekNo);
        long regIdLong = Long.parseLong(regId);
        DateFormat Day = new SimpleDateFormat("dd/MM/yyyy hh:mm");
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
}
