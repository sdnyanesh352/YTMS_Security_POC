package com.yash.ytms_security_poc.security.jwt;

import com.yash.ytms_security_poc.dto.YtmsUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {

    private String token;

    private YtmsUserDto usersDto;
}
