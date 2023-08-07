package org.itsci.project.controller;


import org.itsci.project.model.Subject;
import org.itsci.project.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subject")
public class SubjectConterller {

    @Autowired
    private SubjectService subjectService;

    //listSubject
    @RequestMapping("/list")
    public ResponseEntity listAllSubject (){
        try {
            List<Subject> subjects = subjectService.getAllSubject();
            return new ResponseEntity<>(subjects , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list Subject!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/getbyid/{subjectId}")
    public ResponseEntity getMemberById (@PathVariable("subjectId") String subjectId){
        try {
            Subject subject = subjectService.getSubjectById(subjectId);
            return new ResponseEntity<>(subject , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get subject!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity addMember(@RequestBody Map<String,String> map){
        try {
            Subject member = subjectService.saveSubject(map);
            return new ResponseEntity<>(member, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Add Member!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
