package org.itsci.project.controller;

import org.itsci.project.model.AttendanceSchedule;
import org.itsci.project.model.Course;
import org.itsci.project.service.AttendanceScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendanceschedule")
public class AttendanceScheduleController {

    @Autowired
    private AttendanceScheduleService attendanceScheduleService;

    @RequestMapping("/list")
    public ResponseEntity get_ListAttendanceSchedule (){
        try {
            List<AttendanceSchedule> attendanceSchedules = attendanceScheduleService.get_ListAttendanceSchedule();
            return new ResponseEntity<>(attendanceSchedules , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list AttendanceSchedule!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/getbyweek/{week}/{secid}")
    public ResponseEntity get_AttendanceScheduleByWeek (@PathVariable("week") String week,@PathVariable("secid") String secid){
        try {
            List<AttendanceSchedule>  attendanceSchedule = attendanceScheduleService.get_AttendanceScheduleByWeek(week,secid);
            return new ResponseEntity<>(attendanceSchedule , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get AttendanceSchedule by week!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/get_AttendanceStudent/{week}/{secid}/{userID}")
    public ResponseEntity get_ListAttendanceStudent (@PathVariable("week") String week,@PathVariable("secid") String secid ,@PathVariable("userID") String userID){
        try {
            List<AttendanceSchedule>  attendanceSchedule = attendanceScheduleService.get_AttendanceStudent(week,secid,userID);
            return new ResponseEntity<>(attendanceSchedule , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get AttendanceSchedule by Student!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping("/listbyregistrationid/{RegistrationId}")
    public ResponseEntity get_ListattendanceSchedulesByRegistrationId(@PathVariable("RegistrationId") Long registrationid){
        try {
            List<AttendanceSchedule> attendanceSchedules = attendanceScheduleService.get_ListAttendanceScheduleByRegistrationId(registrationid);

            return new ResponseEntity<>(attendanceSchedules, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list AttendanceSchedule by RegistrationId!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/getbyid/{id}")
    public ResponseEntity get_AttendanceScheduleById (@PathVariable("id") Long id){
        try {
            AttendanceSchedule attendanceSchedule = attendanceScheduleService.get_AttendanceScheduleById(id);
            return new ResponseEntity<>(attendanceSchedule , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get AttendanceSchedule by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/checkscanned/{regId}/{weekNo}")
    public ResponseEntity get_AttendanceScheduleById (@PathVariable("regId") Long regId,@PathVariable("weekNo") String weekNo){
        try {
            AttendanceSchedule attendanceSchedule = attendanceScheduleService.get_AttendanceScheduleCheckScan(regId,weekNo);
            return new ResponseEntity<>(attendanceSchedule , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get AttendanceSchedule by RegId and Week!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity add_AttendanceSchedule (@RequestBody Map<String,String> map){
        try {
            AttendanceSchedule attendanceSchedule = attendanceScheduleService.add_AttendanceSchedule(map);
            return new ResponseEntity<>(attendanceSchedule, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Add AttendanceSchedule!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update_AttendanceSchedule (@RequestBody Map<String,String> map){
        try {
            AttendanceSchedule update_attendanceschedule = attendanceScheduleService.update_AttendanceSchedule(map);
            return new ResponseEntity<>(update_attendanceschedule, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update AttendanceSchedule!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delet_AttendanceSchedule (@PathVariable("id") Long id){
        try {
            AttendanceSchedule attendanceSchedule = attendanceScheduleService.get_AttendanceScheduleById(id);
            Long attendanceScheduleId = attendanceSchedule.getId();
            attendanceScheduleService.delet_AttendanceSchedule(id);

            return new ResponseEntity<>("AttendanceSchedule " + attendanceScheduleId + " was deleted!", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete AttendanceSchedule by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatestastus")
    public ResponseEntity update_AttendanceSchedules(@RequestBody List<Map<String, String>> scheduleList) {
        try {
            List<AttendanceSchedule> updatedSchedules = new ArrayList<>();

            for (Map<String, String> map : scheduleList) {
                AttendanceSchedule update_attendanceschedule = attendanceScheduleService.update_AttendanceStatus(map);
                updatedSchedules.add(update_attendanceschedule);
            }

            return new ResponseEntity<>(updatedSchedules, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update AttendanceSchedules!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
