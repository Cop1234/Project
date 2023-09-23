package org.itsci.project.repository;

import org.itsci.project.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByUserId(Long id);
}
