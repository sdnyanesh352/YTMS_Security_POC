package com.yash.ytms_security_poc.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Project Name - YTMS_Security_POC
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 28-12-2023
 */
@Configuration
public class CommonConfigs {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
