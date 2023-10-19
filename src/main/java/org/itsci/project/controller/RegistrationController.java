package org.itsci.project.controller;


import org.itsci.project.model.Course;
import org.itsci.project.model.Registration;
import org.itsci.project.model.Section;
import org.itsci.project.model.User;
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

    //listRegistration
    @RequestMapping("/list")
    public ResponseEntity get_ListRegistration (){
        try {
            List<Registration> registrations = registrationService.get_ListRegistration();
            return new ResponseEntity<>(registrations , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list Registration!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

    @RequestMapping("/getregid/{sectionid}/{iduser}")
    public ResponseEntity get_RegistrationIdBySectionIdandIdUser(@PathVariable("sectionid") String sectionid,@PathVariable("iduser") String iduser){
        try {
            Registration registrations = registrationService.get_RegistrationIdBySectionIdandIdUser(Long.parseLong(sectionid),Long.parseLong(iduser));
            return new ResponseEntity<>(registrations, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get RegId by sectionid and iduser!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delet_Registration (@PathVariable("id") String id){
        try {
            Long regid = Long.parseLong(id);
            Registration registration = registrationService.get_RegistrationById(regid);
            Long registrationId = registration.getId();
            registrationService.delet_Registration(regid);

            return new ResponseEntity<>("Registration " + registrationId + " was deleted!", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete Registration by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/do_getViewStudent/{idsec}")
    public ResponseEntity GetViewStudent(@PathVariable("idsec") String idsec) {
        try {
            List<Registration> registrations = registrationService.do_getViewStudent(idsec);
            return new ResponseEntity<>(registrations , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list student!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/do_update")
    public ResponseEntity do_update(@RequestBody Map<String,String> map){
        try {
            Registration registration = registrationService.do_update(map);
            return new ResponseEntity<>(registration, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Add Teacher!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
