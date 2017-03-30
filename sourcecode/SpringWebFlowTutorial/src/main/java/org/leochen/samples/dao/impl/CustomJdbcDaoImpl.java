package org.leochen.samples.dao.impl;

import org.leochen.samples.dao.CustomJdbcDao;
import org.leochen.samples.service.user.ChangePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

/**
 * User: leochen
 * Date: 11-12-2
 * Time: 下午1:55
 */
public class CustomJdbcDaoImpl extends JdbcDaoImpl implements CustomJdbcDao {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SaltSource saltSource;



    public boolean changePassword(String username, String oldPassword, String newPassword) {
        UserDetails user = loadUserByUsername(username);
        String password=user.getPassword();
        oldPassword = passwordEncoder.encodePassword(oldPassword,saltSource.getSalt(user));
        if(!oldPassword.equals(password)){
            return false;
        }
        String encodedPassword = passwordEncoder.encodePassword(newPassword,saltSource.getSalt(user));
        getJdbcTemplate().update("update users set password=? where username=?",encodedPassword,username);
        return true;
    }
}
