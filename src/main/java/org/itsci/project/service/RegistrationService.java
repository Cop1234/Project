package org.itsci.project.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface RegistrationService {
    //import User Student
    //void Import_Student(MultipartFile file) throws IOException;
    void Import_Student(MultipartFile file,String id) throws IOException, ParseException;
}
