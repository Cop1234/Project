package org.itsci.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	private Long id;

	private String roomName;
	private String building;
	private double latitude;
	private double longitude;

}