package org.itsci.project.repository;


import org.itsci.project.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,String> {
        List<Subject> getMembersBySubjectNameContainingIgnoreCase (String subjectName);
}
