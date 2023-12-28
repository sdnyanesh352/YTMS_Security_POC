package com.yash.ytms_security_poc.services.ServiceImpls;

import com.yash.ytms_security_poc.dto.YtmsUserDto;
import com.yash.ytms_security_poc.exception.ApplicationException;
import com.yash.ytms_security_poc.model.YtmsUser;
import com.yash.ytms_security_poc.repository.YtmsUserRepository;
import com.yash.ytms_security_poc.services.IServices.IYtmsUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project Name - YTMS_Security_POC
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 28-12-2023
 */
@Service
public class YtmsUserServiceImpl implements IYtmsUserService {

    @Autowired
    private YtmsUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public YtmsUserDto getUserByEmailAdd(String emailAdd) {
        YtmsUserDto userDto = null;
        YtmsUser user = null;

        if (StringUtils.isNotEmpty(emailAdd)) {
            user = this.userRepository.getUserByEmail(emailAdd);

            userDto = this
                    .modelMapper
                    .map(user, YtmsUserDto.class);
        } else {
            throw new ApplicationException("Email add is empty");
        }
        return userDto;
    }

    @Override
    public YtmsUserDto getUserById(Long id) {
        YtmsUserDto userDto = null;
        YtmsUser user = null;

        if (ObjectUtils.isNotEmpty(id)) {
            user = this.userRepository.getUserById(id);

            userDto = this
                    .modelMapper
                    .map(user, YtmsUserDto.class);
        } else {
            throw new ApplicationException("Id is empty");
        }
        return userDto;
    }
}
