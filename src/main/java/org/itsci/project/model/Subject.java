package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
;

import java.util.Set;

@Entity
@Table(name = "subject")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Subject {

	@Id
	private Long id;

	private String subjectId;
	private String subjectName;
	private String detail;
	private int credit;


//	public Subject(String subjectId, String subjectName, String detail, int credit) {
//		this.subjectId = subjectId;
//	}
}
