package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "logins")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	private Long id;

	private String username;
	private String password;

	@JsonManagedReference
	@ManyToMany ( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "authorities_logins",
			joinColumns = {
				@JoinColumn(name = "Login_id",referencedColumnName = "id")
			},
			inverseJoinColumns = {
				@JoinColumn(name = "authority_id",referencedColumnName = "id")
			})
	private Set<Authority> role = new HashSet<>();

}
