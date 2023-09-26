package org.itsci.project.service;

import org.itsci.project.model.Section;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface SectionService {

    //CRUD 5 KINDS

    //GET ALL
    List<Section> get_ListSection();

    List<Section> get_ListSectionByIdUser(Long iduser);

    //GET BY ID
    Section get_SectionById(Long id);

    //CREATE
    Section add_Section(Map<String,String> map) throws ParseException;

    //Update
    Section update_Section (Map<String,String> map) throws ParseException;

    //Delete
    void delet_Section(Long id);
}
