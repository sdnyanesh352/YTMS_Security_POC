package com.yash.ytms_security_poc.controller;

import com.yash.ytms_security_poc.dto.YtmsUserDto;
import com.yash.ytms_security_poc.security.jwt.JwtAuthRequest;
import com.yash.ytms_security_poc.security.jwt.JwtAuthResponse;
import com.yash.ytms_security_poc.services.IServices.IAuthService;
import com.yash.ytms_security_poc.services.IServices.IYtmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project Name - YTMS_Security_POC
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 28-12-2023
 */
@RestController
@RequestMapping("/ytms")
public class LoginSignUpController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private IYtmsUserService ytmsUserService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest authRequest) {
        JwtAuthResponse authResponse = this
                .authService
                .login(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @GetMapping("/get/user/{id}")
    public ResponseEntity<YtmsUserDto> login(@PathVariable(required = true) Long id) {
        YtmsUserDto user = this
                .ytmsUserService
                .getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
