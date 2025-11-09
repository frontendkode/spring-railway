package com.KissTech.crm.controller;

import com.KissTech.crm.DTO.*;
import com.KissTech.crm.Exception.UserNotFoundException;
import com.KissTech.crm.apiService.AuthApiService;
import com.KissTech.crm.apiService.UserApiService;
import com.KissTech.crm.model.User;
import com.KissTech.crm.utils.RestResponse;
import com.KissTech.crm.utils.RestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.AuthenticationException;
import java.security.Key;
import java.util.Base64;

@Tag(name = "Authentication Apis", description = "Manage  Authentication Apis  information")
@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthApiService authApiService;

    private final UserApiService userApiService;

    private final ObjectMapper objectMapper;

    public AuthController(AuthApiService authApiService, UserApiService userApiService, ObjectMapper objectMapper) {
        super();
        this.authApiService = authApiService;
        this.userApiService = userApiService;
        this.objectMapper = objectMapper;
    }



    private static final String AES_ALGORITHM = "AES";
    private static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String AES_SECRET_KEY = "WGqTO5iRgcgRXNxg";

    public String encrypt(String data) throws Exception {
        Key key = new SecretKeySpec(AES_SECRET_KEY.getBytes(), AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedData) throws Exception {
        byte[] keyBytes = AES_SECRET_KEY.getBytes();
        Key key = new SecretKeySpec(keyBytes, AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }



//
//    @PostMapping("/auth/signIn")
//    public ResponseEntity<RestResponse<JwtDTO>> save(@RequestBody LoginRequestDTO loginRequestDTO) {
//
//        return RestUtils.successResponse(authApiService.authenticateUser(loginRequestDTO));
//
//    }



//
//    @PostMapping("/auth/signIn1")
//    public ResponseEntity<RestResponse<RespoDTO>> signIn(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
//
//
//
//
//
//        if (loginRequestDTO != null && loginRequestDTO.getEncData() != null) {
//            String encData = loginRequestDTO.getEncData();
//            String data = decrypt(encData);
//
//            JSONObject jsonObject = new JSONObject(data);
//            String username = jsonObject.getString("username");
//            String password = jsonObject.getString("password");
//
//            loginRequestDTO.setUsername(username);
//            loginRequestDTO.setPassword(password);
//            System.out.println(loginRequestDTO);
//            return RestUtils.successResponse(authApiService.authenticateUser(loginRequestDTO));
//
//        }
//
//        return ResponseEntity
//                .badRequest()
//                .body(new RestResponse<>("Failed",null,false,401));
//    }

    // Make sure this is configured as a Spring bean

    @PostMapping("/auth/signIn2")
    public String signIn2(@RequestBody LoginRequestDTO loginRequestDTO) throws JsonProcessingException {
        try {
            if (loginRequestDTO != null && loginRequestDTO.getEncData() != null) {
                String data = decrypt(loginRequestDTO.getEncData());

                JSONObject jsonObject = new JSONObject(data);
                String username = jsonObject.optString("username", null);
                String password = jsonObject.optString("password", null);

                if (username == null || password == null) {
                    return buildEncryptedResponse("Invalid Credentials", null, false, 401);
                }

                loginRequestDTO.setUsername(username);
                loginRequestDTO.setPassword(password);

                RestResponse<JwtDTO> response = RestUtils
                        .successResponse(authApiService.authenticateUser(loginRequestDTO))
                        .getBody();

                return encrypt(objectMapper.writeValueAsString(response));
            }

            return buildEncryptedResponse("Invalid Request", null, false, 400);

        } catch (JSONException e) {
            return buildEncryptedResponse("Invalid JSON format", null, false, 400);
        } catch (AuthenticationException e) {
            return buildEncryptedResponse("Authentication failed", null, false, 401);
        } catch (Exception e) {
            e.printStackTrace(); // keep for debugging/logging
            return buildEncryptedResponse("Server error", null, false, 500);
        }
    }

    private String buildEncryptedResponse(String message, Object data, boolean success, int code) {
        try {
            RestResponse<Object> response = new RestResponse<>(message, data, success, code);
            return encrypt(objectMapper.writeValueAsString(response));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    @PostMapping("/auth/forgotPassword")
    public ResponseEntity<RestResponse<RespoDTO>> forgotPassword(@RequestParam String username) {

        User user = userApiService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RestResponse<>("Username not found", null, false, HttpStatus.NOT_FOUND.value()));
        }

        try {
            RespoDTO response = authApiService.authenticateUser(username);
            return ResponseEntity
                    .ok(new RestResponse<>("OTP sent successfully", response, true, HttpStatus.OK.value()));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RestResponse<>("Username not found", null, false, HttpStatus.BAD_REQUEST.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RestResponse<>("An error occurred while processing the request" + e.getMessage(), null,
                            false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    @PostMapping("/auth/resetPasswordToken")
    public ResponseEntity<ResponseEntity<RestResponse<MessageResponseDTO>>> resetrPasssword(
            @RequestBody ResetPasswordDTO resetPasswordDTO) {
        MessageResponseDTO reset = authApiService.resetPassword(resetPasswordDTO);
        return ResponseEntity.ok().body(RestUtils.successResponse(reset));
    }




    @PostMapping("/decrypt")
    public String decrypt (@RequestBody LoginRequestDTO loginRequestDTO){

        try {
            String data = decrypt(loginRequestDTO.getEncData());
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

}