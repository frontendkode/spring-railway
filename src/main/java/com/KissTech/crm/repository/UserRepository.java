package com.KissTech.crm.repository;

import com.KissTech.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserRepository  extends JpaRepository<User, UUID>{

	User findByUsername(String username);

	User findByToken(String token);

	boolean existsByUsername(String username);


}
         