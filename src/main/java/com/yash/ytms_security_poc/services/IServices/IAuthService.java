package com.yash.ytms_security_poc.services.IServices;


import com.yash.ytms_security_poc.security.jwt.JwtAuthRequest;
import com.yash.ytms_security_poc.security.jwt.JwtAuthResponse;

public interface IAuthService {

    JwtAuthResponse login(JwtAuthRequest authRequest);

}
