package org.itsci.project.repository;

import org.itsci.project.model.AttendanceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceScheduleRepository extends JpaRepository<AttendanceSchedule, Long> {
    List<AttendanceSchedule> findByRegistrationIdOrderByWeekNo(Long registrationid);
    AttendanceSchedule findByRegistrationIdAndWeekNo(Long registrationid, int week);
    List<AttendanceSchedule> findByWeekNoAndRegistration_Section_IdOrderByRegistration_User_Id(int week, Long sectionId);
    List<AttendanceSchedule> findByWeekNoAndRegistration_Section_IdAndRegistration_User_IdOrderByRegistration_User_Id(int week, Long sectionId, Long idUser);
}
