package org.itsci.project.controller;


import org.itsci.project.model.Course;
import org.itsci.project.model.Registration;
import org.itsci.project.model.Section;
import org.itsci.project.repository.RegistrationRepository;
import org.itsci.project.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;


    //Teacher : Import_Student
    @PostMapping("/upload")
    public ResponseEntity uploadExcelFile(@RequestParam("file") MultipartFile file,@RequestParam("id") String id) {
        try {
            System.out.println("FILE NAME IS : " + file.getOriginalFilename());
            registrationService.Import_Student(file,id);
            return new ResponseEntity<>("List student success", HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>("Failed to list student!", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //Student : get_ViewSubject
    @RequestMapping("/stu_listsubject/{IdUser}")
    public ResponseEntity get_ViewSubject(@PathVariable("IdUser") String IdUser){
        try {
            List<Registration> registrations = registrationService.get_ViewSubject(IdUser);
            return new ResponseEntity<>(registrations, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list Subject by IdUser!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delet_Registration (@PathVariable("id") Long id){
        try {
            Registration registration = registrationService.get_RegistrationById(id);
            Long registrationId = registration.getId();
            registrationService.delet_Registration(id);

            return new ResponseEntity<>("Course " + registrationId + " was deleted!", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete Registration by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
