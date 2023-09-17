package org.itsci.project.repository;

import org.itsci.project.model.Course;
import org.itsci.project.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, String> {
    //Optional<Login> findById (Long id);
}
