package org.itsci.project.service;

import org.itsci.project.model.Subject;
import org.itsci.project.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectService subjectService;

    @Override
    public List<Subject> getAllSubject() {
        return null;
    }

    @Override
    public Subject getSubjectById(String id) {
        return null;
    }

    @Override
    public Subject saveSubject(Map<String, String> map) {
        return null;
    }

    @Override
    public Subject updateSubject(Subject subject) {
        return null;
    }

    @Override
    public void deletSubject(String id) {

    }

    @Override
    public List<Subject> getMembersBySubjectNameContainingIgnoreCase(String subjectName) {
        return null;
    }
    //
}
