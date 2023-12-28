package com.yash.ytms_security_poc.repository;

import com.yash.ytms_security_poc.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Project Name - YTMS_Security_POC
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 28-12-2023
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query("select ur from UserRole ur where ur.roleId=?1")
    UserRole getUserRoleByRoleId(Long roleId);

    @Query("select ur from UserRole ur where ur.roleTypes=?1")
    UserRole getUserRoleByRoleName(String roleType);

    @Query("select ur from UserRole ur")
    Set<UserRole> getAllUserRoles();
}
