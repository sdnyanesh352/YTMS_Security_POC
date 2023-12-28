package com.yash.ytms_security_poc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project Name - YTMS_Security_POC
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 28-12-2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {

    private Long roleId;

    private String roleTypes;
}
