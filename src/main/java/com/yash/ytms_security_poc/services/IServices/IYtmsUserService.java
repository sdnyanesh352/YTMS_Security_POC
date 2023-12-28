package com.yash.ytms_security_poc.services.IServices;

import com.yash.ytms_security_poc.dto.YtmsUserDto;

/**
 * Project Name - YTMS_Security_POC
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 28-12-2023
 */
public interface IYtmsUserService {
    
    YtmsUserDto getUserByEmailAdd(String emailAdd);

    YtmsUserDto getUserById(Long id);
}
