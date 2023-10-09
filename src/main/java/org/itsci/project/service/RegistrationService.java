package org.itsci.project.service;

import org.itsci.project.model.Registration;
import org.itsci.project.model.Room;
import org.itsci.project.model.Section;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface RegistrationService {
    //import User Student
    //void Import_Student(MultipartFile file) throws IOException;
    void Import_Student(MultipartFile file,String id) throws IOException, ParseException;

    //Registration get_ViewSubject(String id);
    List<Registration> get_ViewSubject(String iduser);
    List<Registration> do_getViewStudent(String idsection);
    Registration do_update(Map<String,String> map);
    //GET BY ID
    Registration get_RegistrationById(Long id);

    //Update
    Registration update_Registration (Map<String,String> map) throws ParseException;

    //Delete
    void delet_Registration(Long id);

}
