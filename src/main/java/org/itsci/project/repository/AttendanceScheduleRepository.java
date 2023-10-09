package org.itsci.project.repository;

import org.itsci.project.model.AttendanceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceScheduleRepository extends JpaRepository<AttendanceSchedule, Long> {
    List<AttendanceSchedule> findByRegistrationId(Long registrationid);
}
