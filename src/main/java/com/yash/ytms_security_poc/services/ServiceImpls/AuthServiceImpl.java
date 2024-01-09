package com.yash.ytms_security_poc.services.ServiceImpls;

import com.yash.ytms_security_poc.exception.ApplicationException;
import com.yash.ytms_security_poc.security.jwt.JwtAuthRequest;
import com.yash.ytms_security_poc.security.jwt.JwtAuthResponse;
import com.yash.ytms_security_poc.security.jwt.JwtTokenHelper;
import com.yash.ytms_security_poc.security.userdetails.CustomUserDetails;
import com.yash.ytms_security_poc.security.userdetails.CustomUserDetailsServiceImpl;
import com.yash.ytms_security_poc.services.IServices.IAuthService;
import com.yash.ytms_security_poc.services.IServices.IYtmsUserService;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private JwtTokenHelper tokenHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private IYtmsUserService userService;

    @Override
    public JwtAuthResponse login(JwtAuthRequest authRequest) {
        String userName = authRequest.getUserName();
        String password = authRequest.getPassword();

        this.authenticate(userName, password);

        CustomUserDetails userDetails = this
                .userDetailsService
                .loadUserByUsername(userName);

        Assert.notNull(userDetails);

        String token = this
                .tokenHelper
                .generateToken(userDetails);

        return new JwtAuthResponse(token);
    }

    private void authenticate(String userName,
                              String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException credentialsException) {
            throw new ApplicationException("Invalid username or password");
        }
    }
}
