package org.itsci.project.service;

import org.itsci.project.model.AttendanceSchedule;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface AttendanceScheduleService {

    //CRUD 5 KINDS

    //GET ALL
    List<AttendanceSchedule> get_ListAttendanceSchedule ();

    //GET ALL BY IDUSER
    List<AttendanceSchedule> get_ListAttendanceScheduleByRegistrationId(Long registrationid);

    //GET BY ID
    AttendanceSchedule get_AttendanceScheduleById(Long id);

    //GET BY week
    List<AttendanceSchedule> get_AttendanceScheduleByWeek(String id);

    //CREATE
    AttendanceSchedule add_AttendanceSchedule(Map<String,String> map) throws ParseException;

    //Update
    AttendanceSchedule update_AttendanceSchedule (Map<String,String> map) throws ParseException;

    //Delete
    void delet_AttendanceSchedule(Long id);
}
