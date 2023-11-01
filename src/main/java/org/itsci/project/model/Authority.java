package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authority")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Authority {

	@Id
	private Long id;

	@Column(nullable = false)
	private String role;

	@JsonBackReference
	@ManyToMany (mappedBy = "role", fetch = FetchType.LAZY)
	private Set<Login> username = new HashSet<>();

	public Authority(Long id, String role) {
		this.id = id;
		this.role = role;
	}
}

