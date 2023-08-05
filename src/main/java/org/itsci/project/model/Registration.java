package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registration")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Registration {
	
	@Id
	private Long id;
	private String regisStatus;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "sectionID",referencedColumnName = "id")
	private Section section;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "userID")
	private User user;
	
	
}
