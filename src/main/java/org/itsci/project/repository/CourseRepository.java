package org.itsci.project.repository;

import org.itsci.project.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByUserId(Long iduser);
    //Optional<Login> findById (Long id);
}
