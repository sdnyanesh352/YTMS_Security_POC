package com.yash.ytms_security_poc.services.ServiceImpls;

import com.yash.ytms_security_poc.constants.UserRoleTypes;
import com.yash.ytms_security_poc.dto.UserRoleDto;
import com.yash.ytms_security_poc.dto.YtmsUserDto;
import com.yash.ytms_security_poc.exception.ApplicationException;
import com.yash.ytms_security_poc.model.YtmsUser;
import com.yash.ytms_security_poc.repository.YtmsUserRepository;
import com.yash.ytms_security_poc.services.IServices.IUserRoleService;
import com.yash.ytms_security_poc.services.IServices.IYtmsUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private IUserRoleService userRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public YtmsUserDto createNewUser(YtmsUserDto userDto) {
        YtmsUser user = null;
        if (ObjectUtils.isNotEmpty(userDto)) {

            user = this
                    .userRepository
                    .getUserByEmail(userDto.getEmailAdd());

            if (ObjectUtils.isEmpty(user)) {
                //user do not exist, new user will be created
                if (StringUtils.equals(userDto.getPassword(), userDto.getConfirmPassword())) {

                    UserRoleDto userRoleDto = this
                            .userRoleService
                            .getUserRoleByRoleName(UserRoleTypes.ROLE_NORMAL_USER.toString());

                    userDto.setUserRole(userRoleDto);

                    user = this
                            .modelMapper
                            .map(userDto, YtmsUser.class);

                    user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));

                    //reassigned with the new created data
                    user = this
                            .userRepository
                            .save(user);

                    //re-assigning the dto class with the new data
                    userDto = this
                            .modelMapper
                            .map(user, YtmsUserDto.class);
                } else {
                    throw new ApplicationException("Password did not matched, please try again");
                }
            } else {
                throw new ApplicationException("User already exists with this email address");
            }
        } else {
            throw new ApplicationException("Invalid user details");
        }

        return userDto;
    }

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
}
