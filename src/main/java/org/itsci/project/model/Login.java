package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

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

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@JsonManagedReference
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinTable(name = "authorities_logins",
			joinColumns = {
				@JoinColumn(name = "Login_id",referencedColumnName = "id")
			},
			inverseJoinColumns = {
				@JoinColumn(name = "authority_id",referencedColumnName = "id")
			})
	private Set<Authority> role = new HashSet<>();

}
