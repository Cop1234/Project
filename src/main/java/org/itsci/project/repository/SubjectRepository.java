package org.itsci.project.repository;


import org.itsci.project.model.Login;
import org.itsci.project.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,String> {
        List<Subject> getSubjectsBySubjectNameContainingIgnoreCase (String subjectName);

        //Optional<Subject> findById(long );
}
