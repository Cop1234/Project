package org.itsci.project.repository;

import org.itsci.project.model.Section;
import org.itsci.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByUserId(Long id);
    Optional<Section> findById(Long id);
}
