package org.itsci.project.repository;

import org.itsci.project.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Optional<Authority> findById (Long id);

    List<Authority> findByRole (String role);
}
