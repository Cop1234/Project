package org.itsci.project.controller;

import org.itsci.project.model.Subject;
import org.itsci.project.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //listSubject
    @RequestMapping("/list")
    public ResponseEntity get_ListDataSubject (){
        try {
            List<Subject> subjects = subjectService.get_ListDataSubject();
            return new ResponseEntity<>(subjects , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list Subject!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //getid
    @RequestMapping("/getbyid/{subjectId}")
    public ResponseEntity get_DataSubject (@PathVariable("subjectId") String subjectId){
        try {
            Subject subject = subjectService.get_DataSubject(subjectId);
            return new ResponseEntity<>(subject , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Subject!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //add subject
    @RequestMapping("/add")
    public ResponseEntity add_DataSubject(@RequestBody Map<String,String> map){
        try {
            Subject subject = subjectService.add_DataSubject(map);
            return new ResponseEntity<>(subject, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Add Subject!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //ubdate subject
    @PutMapping("/update")
    public ResponseEntity update_DataSubject(@RequestBody Subject subject) {
        try {
            Subject updateSubject = subjectService.update_DataSubject(subject);
            return new ResponseEntity<>(updateSubject, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update Subject!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //delete subject
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delet_Subject (@PathVariable("id") String id){
        try {
            //เรียก SubjectById
            Subject subject = subjectService.get_DataSubject(id);
            String subjectId = subject.getSubjectId();
            //ลบ
            subjectService.delet_Subject(id);

            return new ResponseEntity<>("Subject " + subjectId + " was deleted!", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete Subject by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //หาข้อมูลทั้งหมดด้วยตัวอักษร
    @GetMapping("/getbycontname/{subjectName}")
    public ResponseEntity getSubjectsBySubjectNameContainingIgnoreCase (@PathVariable("subjectName") String subjectName){
        try {
            List<Subject> subjects = subjectService.getSubjectsBySubjectNameContainingIgnoreCase(subjectName);
            return new ResponseEntity<>(subjects, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to getSubject by name", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
