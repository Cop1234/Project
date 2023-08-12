package org.itsci.project.service;

import org.itsci.project.model.Subject;

import java.util.List;
import java.util.Map;

public interface SubjectService {
    //CRUD 5 KINDS

    //GET ALL
    List<Subject> get_ListDataSubject ();

    //GET BY ID
    Subject get_DataSubject(String id);

    //CREATE
    Subject add_DataSubject(Map<String,String> map);

    //Update
    Subject update_DataSubject (Subject subject);

    //Delete
    void delet_Subject(String id);

    //GET BY CONTAINING NAME
    List<Subject> getSubjectsBySubjectNameContainingIgnoreCase (String subjectName);

}
