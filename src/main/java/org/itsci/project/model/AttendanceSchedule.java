package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "attendanceschedule")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AttendanceSchedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "registration_id", referencedColumnName = "id")
	@JsonIgnoreProperties("attendanceSchedules")
	private Registration registration;

	@Column(nullable = false)
	private int weekNo;

	@Column(nullable = false)
	private Date checkInTime;

	@Column(nullable = false)
	private String status;

}