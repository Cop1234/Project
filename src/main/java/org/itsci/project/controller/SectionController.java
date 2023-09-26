package org.itsci.project.controller;

import org.itsci.project.model.Section;
import org.itsci.project.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @RequestMapping("/list")
    public ResponseEntity get_ListSection (){
        try {
            List<Section> sections = sectionService.get_ListSection();
            return new ResponseEntity<>(sections , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list Section!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/listbyiduser/{IdUser}")
    public ResponseEntity get_ListSectionByIdUser(@PathVariable("IdUser") Long IdUser){
        try {
            List<Section> sections = sectionService.get_ListSectionByIdUser(IdUser);
            return new ResponseEntity<>(sections, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list Section by IdUser!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/getbyid/{id}")
    public ResponseEntity get_SectionById (@PathVariable("id") Long id){
        try {
            Section section = sectionService.get_SectionById(id);
            return new ResponseEntity<>(section , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Section by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity add_Section (@RequestBody Map<String,String> map){
        try {
            Section section = sectionService.add_Section(map);
            return new ResponseEntity<>(section, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Add Section!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update_Section (@RequestBody Map<String,String> map){
        try {
            Section update_section = sectionService.update_Section(map);
            return new ResponseEntity<>(update_section, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update Section!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delet_Section (@PathVariable("id") Long id){
        try {
            Section section = sectionService.get_SectionById(id);
            Long sectionId = section.getId();
            sectionService.delet_Section(id);

            return new ResponseEntity<>("Section " + sectionId + " was deleted!", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete Section by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
