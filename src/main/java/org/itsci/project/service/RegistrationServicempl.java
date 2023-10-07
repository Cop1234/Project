package org.itsci.project.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.itsci.project.model.*;
import org.itsci.project.repository.RegistrationRepository;
import org.itsci.project.repository.SectionRepository;
import org.itsci.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServicempl implements RegistrationService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public void Import_Student(MultipartFile file,String id) throws  IOException {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = 0; // ใช้ตรวจสอบว่าเราอยู่ที่แถวที่ 0 หรือไม่
        for (Row row : sheet) {
            if (rowNum == 0) {
                // ข้ามการอ่านข้อมูลในแถวที่ 0
                rowNum++;
                continue;
            }
            String userid = row.getCell(0).getStringCellValue();
            String fname = row.getCell(1).getStringCellValue();
            String lname = row.getCell(2).getStringCellValue();

            String regisStatus = row.getCell(3).getStringCellValue();
                    //"ลงทะเบียนเรียน";
            Section section_id = sectionRepository.findById(Long.parseLong(id)).get();
            User userUserid = studentRepository.findByuserid(userid).get();
            Long idUser = userUserid.getId();
            Registration registration = registrationRepository.findByUser_Id(idUser).get();
            Long reg_idSec = registration.getSection().getId();
            Long idReg = registration.getId();


            if (userUserid.getFname().equals(fname)){
                    if (userUserid.getLname().equals(lname)){
                        if (userUserid.getUserid().equals(userid)){
                            if (!reg_idSec.equals(section_id.getId())) {
                                Registration registrations = new Registration();
                                registrations.setSection(section_id);
                                registrations.setUser(userUserid);
                                registrations.setRegisStatus(regisStatus);
                                registrationRepository.save(registrations);
                            }else {

                               Registration regs =  registrationRepository.findById(idReg).get();
                               regs.setRegisStatus(regisStatus);
                               registrationRepository.save(regs);
                            }

                        }else {
                            System.out.println("null value userid! "+userid);
                        }
                    }else {
                        System.out.println("null value lname! "+lname);
                    }
                }else {
                    System.out.println("null value fname!" + fname);
                }
            rowNum++;
        }
        workbook.close();
    }



    @Override
    public List<Registration> get_ViewSubject(String iduser) {
        Long id = Long.parseLong(iduser);
        return registrationRepository.findByUserId(id);
    }


}
