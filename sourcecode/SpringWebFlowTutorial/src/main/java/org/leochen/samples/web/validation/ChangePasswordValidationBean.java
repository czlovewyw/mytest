package org.leochen.samples.web.validation;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * User: leochen
 * Date: 11-12-8
 * Time: 下午12:39
 */

@Matches(field = "newPassword",verifyField = "confirmNewPassword",message = "{constraint.confirmNewPassword.not.match.newPassword}")
public class ChangePasswordValidationBean  {
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;



    @NotBlank(message = "{constraint.oldPassword.must.not.be.empty}")
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @NotBlank
    @Size(min = 5, message = "{constraint.newPassword.size.message}")
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @NotBlank
    @Size(min = 5, message = "{constraint.confirmNewPassword.size.message}")
    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

}

