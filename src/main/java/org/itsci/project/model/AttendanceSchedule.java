package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "attendanceSchedule")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AttendanceSchedule {
	
	@Id
	private Long id;

	private String subjectID;
	private String studentID;
	private int weekNo;
	private String type;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="sectionid", nullable=false)
    private Section section;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="roomid", nullable=false)
	private Room room;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name="roomName")
//	private List<Room> room;
	

}