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
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> get_ListDataSubject() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject get_DataSubject(String subjectId) {
        return subjectRepository.getReferenceById(subjectId);
    }

    @Override
    public Subject add_DataSubject(Map<String, String> map) {
        String id = map.get("id");
        String subjectId = map.get("subjectId");
        String subjectName = map.get("subjectName");
        String detail = map.get("detail");
        String credit = map.get("credit");

        Long idConvert = Long.parseLong(id);
        int creditConvert = Integer.parseInt(credit);

        //Create new object
        Subject subject = new Subject(idConvert,subjectId,subjectName,detail,creditConvert);

        //Save Object to DB
        return subjectRepository.save(subject);
    }

    @Override
    public Subject update_DataSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void delet_Subject(String id) {
        Subject Id= subjectRepository.getReferenceById(id);

        subjectRepository.delete(Id);
        subjectRepository.findAll();

    }

    @Override
    public List<Subject> getSubjectsBySubjectNameContainingIgnoreCase(String subjectName) {
        return subjectRepository.getSubjectsBySubjectNameContainingIgnoreCase(subjectName);
    }

}
