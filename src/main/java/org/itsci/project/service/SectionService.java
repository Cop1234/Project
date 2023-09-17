package org.itsci.project.service;

import org.itsci.project.model.Section;

import java.util.List;
import java.util.Map;

public interface SectionService {

    //CRUD 5 KINDS

    //GET ALL
    List<Section> get_ListSection();

    //GET BY ID
    Section get_SectionById(String id);

    //CREATE
    Section add_Section(Map<String,String> map);

    //Update
    Section update_Section (Section section);

    //Delete
    void delet_Section(String id);
}
