package com.yash.ytms_security_poc;

import com.yash.ytms_security_poc.constants.UserRoleTypes;
import com.yash.ytms_security_poc.model.UserRole;
import com.yash.ytms_security_poc.model.YtmsUser;
import com.yash.ytms_security_poc.repository.YtmsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class YtmsSecurityPocApplication implements CommandLineRunner {

    @Autowired
    private YtmsUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(YtmsSecurityPocApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /**
         * Comment out the below code at first run
         */
        UserRole role = new UserRole(501L, UserRoleTypes.ROLE_NORMAL_USER.toString());

        YtmsUser user = new YtmsUser(1L,
                "Yash",
                "yash.raj@yash.com",
                this.passwordEncoder.encode("abcde"),
                role);

        this
                .userRepository
                .save(user);
    }
}
