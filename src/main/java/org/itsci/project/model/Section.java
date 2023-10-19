package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "section")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Section implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	private Long id;

	private LocalTime startTime;
	private int duration;
	private String sectionNumber;
	private String type;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, optional = false)
	@JoinColumn(name = "userID")
	private User user;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, optional = false)
	@JoinColumn(name = "course_id")
	private Course course;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, optional = false)
	@JoinColumn(name="roomid", nullable=false)
	private Room room;

	@JsonIgnore
	@OneToMany(mappedBy = "section", cascade = CascadeType.REMOVE)
	private List<Registration> registrations;

}
