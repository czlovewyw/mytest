package org.leochen.samples.service.user;


/**
 * User: leochen
 * Date: 11-12-2
 * Time: 下午1:50
 */
public interface ChangePassword {

    boolean changePassword(String username, String oldPassword, String newPassword);

}
