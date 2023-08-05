package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "studentattendance")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudentAttendance {
	
	@Id
	private Long id;

	@Column(nullable = false)
	private String status;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "attenscheduleID")
	private AttendanceSchedule attendanceschedule;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "regID")
	private Registration registration;


}
