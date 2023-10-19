package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "registration")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Registration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, optional = false)
	@JoinColumn(name = "sectionID",referencedColumnName = "id")
	@JsonIgnoreProperties("registrations")
	private Section section;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, optional = false)
	@JoinColumn(name = "userID")
	private User user;

	@JsonIgnore
	@OneToMany(mappedBy = "registration", cascade = CascadeType.REMOVE)
	private List<AttendanceSchedule> attendanceSchedules;

}
