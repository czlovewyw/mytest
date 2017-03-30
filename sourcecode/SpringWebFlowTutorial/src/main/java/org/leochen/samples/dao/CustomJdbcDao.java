package org.leochen.samples.dao;

import org.leochen.samples.service.user.ChangePassword;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * User: leochen
 * Date: 11-12-8
 * Time: 下午3:10
 */
public interface CustomJdbcDao extends ChangePassword, UserDetailsService {

}
