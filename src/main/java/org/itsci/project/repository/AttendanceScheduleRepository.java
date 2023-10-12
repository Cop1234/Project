package org.itsci.project.repository;

import org.itsci.project.model.AttendanceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceScheduleRepository extends JpaRepository<AttendanceSchedule, Long> {
    List<AttendanceSchedule> findByRegistrationId(Long registrationid);
    List<AttendanceSchedule> findByWeekNoAndRegistration_Section_Id(int week, Long sectionId);
    List<AttendanceSchedule> findByWeekNoAndRegistration_Section_IdAndRegistration_User_Id(int week, Long sectionId, Long idUser);


}
