package org.itsci.project.controller;


import org.itsci.project.repository.RegistrationRepository;
import org.itsci.project.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

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
}