package com.KissTech.crm.model;

import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User extends  AbstractEntity{




	@Column
	private String username;

	@Column
	private String password;

	private String userType;


	@Column
	private String otp;

	private String token;

	private LocalDateTime tokenCreationDate;

	private String role;

}
