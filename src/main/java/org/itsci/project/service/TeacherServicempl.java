package org.itsci.project.service;



import org.itsci.project.model.User;
import org.itsci.project.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServicempl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<User> get_ListTeacher() {
        return teacherRepository.findAll();
    }

//    @Override
//    public User get_Teacher(String fname) {
//        return teacherRepository.getReferenceById(fname);
//    }


}
