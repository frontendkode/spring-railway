package com.KissTech.crm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KissTech.crm.DTO.UserDTO;
import com.KissTech.crm.apiService.UserApiService;
import com.KissTech.crm.createDTO.CreateUserDTO;
import com.KissTech.crm.utils.RestResponse;
import com.KissTech.crm.utils.RestUtils;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Registerd UserApiS" , description="Manged Registered User Api")
@RestController
@RequestMapping("/api")
public class UserController {

	private final UserApiService userApiService;

	public UserController(UserApiService userApiService) {
		super();
		this.userApiService = userApiService;
	}

	@PostMapping("/user/create")
	public ResponseEntity<RestResponse<UserDTO>> save(@RequestBody CreateUserDTO createUserDTO) {

		return RestUtils.successResponse(userApiService.save(createUserDTO));

	}



	@PostMapping("/user/getAllUser")
	public ResponseEntity<RestResponse<List<String>>>getAllUser () {
		List<String> uniqueDivisions = userApiService.getAllUniqueUsers();
		RestResponse<List<String>> response = new RestResponse<>(uniqueDivisions);
		return ResponseEntity.ok(response);
	}

	// OkHttpClient instance

	}


