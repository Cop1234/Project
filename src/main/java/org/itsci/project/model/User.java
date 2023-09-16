package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private String typeuser;
	private String email;
	private String fname;
	private String lname;
	private Date birthdate;
	private String gender;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "loginid")
	private Login login;

}
