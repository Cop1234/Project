package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	private Long id;
	private String userid;

	@Column(nullable = false)
	private String typeuser;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String fname;

	@Column(nullable = false)
	private String lname;

	@Column(nullable = false)
	private Date birthdate;

	@Column(nullable = false)
	private String gender;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "loginid")
	private Login login;

}
