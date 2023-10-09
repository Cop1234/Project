package org.itsci.project.repository;

import org.itsci.project.model.Registration;

import org.itsci.project.model.Section;
import org.itsci.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration,Long> {
    List<Registration> findByUserId(Long id);
    List<Registration> findBySectionId(Long SectionId);
    Optional<Registration> findById(Long id);

    Optional<Registration> findByUser_Id(Long id);
}
