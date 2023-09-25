package org.itsci.project.repository;

import org.itsci.project.model.Registration;

import org.itsci.project.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration,String> {
    List<Registration> findByUserId(Long id);
    Optional<Registration> findById(Long id);


}
