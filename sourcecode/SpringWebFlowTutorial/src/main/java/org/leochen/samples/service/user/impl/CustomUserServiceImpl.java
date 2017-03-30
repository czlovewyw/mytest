package org.leochen.samples.service.user.impl;

import org.leochen.samples.dao.CustomJdbcDao;
import org.leochen.samples.dao.impl.CustomJdbcDaoImpl;
import org.leochen.samples.service.user.CustomUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: leochen
 * Date: 11-12-7
 * Time: 下午2:35
 */
@Service("customUserService")
public class CustomUserServiceImpl implements CustomUserService {
    private CustomJdbcDao customJdbcDao;

    @Resource
    public void setCustomJdbcDao(CustomJdbcDao customJdbcDao) {
        this.customJdbcDao = customJdbcDao;
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        return customJdbcDao.changePassword(username,oldPassword,newPassword);
    }



}
