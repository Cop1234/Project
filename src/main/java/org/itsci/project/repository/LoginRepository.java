package org.itsci.project.repository;

import org.itsci.project.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginRepository extends JpaRepository<Login, String> {
    List<Login> getLoginByUsernameContainingIgnoreCase (String username);
}
