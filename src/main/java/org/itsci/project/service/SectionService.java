package org.itsci.project.service;

import org.itsci.project.model.Section;

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
    Section add_Section(Map<String,String> map);

    //Update
    Section update_Section (Section section);

    //Delete
    void delet_Section(Long id);
}
