package com.yash.ytms_security_poc.repository;

import com.yash.ytms_security_poc.model.YtmsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Project Name - YTMS_Security_POC
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 28-12-2023
 */
@Repository
public interface YtmsUserRepository extends JpaRepository<YtmsUser, Long> {

    @Query("select yur from YtmsUser yur where yur.emailAdd=?1")
    YtmsUser getUserByEmail(String email);
}
