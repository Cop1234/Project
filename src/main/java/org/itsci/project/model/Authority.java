package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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

	@ManyToMany (mappedBy = "Role")
	private Set<Login> username = new HashSet<>();

	public Authority(Long id, String role) {
		this.id = id;
		this.role = role;
	}
}

