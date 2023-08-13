package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "authority")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Authority {

	@Id
	private Long id;
	private String role;

	@ManyToMany
	@JoinTable(
	  name = "Authories_logins",
	  joinColumns = @JoinColumn(name = "authorityID"),
	  inverseJoinColumns = @JoinColumn(name = "loginID"))
	private Set<Login> logins;

	
}
