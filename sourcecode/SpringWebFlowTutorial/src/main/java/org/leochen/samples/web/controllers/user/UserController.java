package org.leochen.samples.web.controllers.user;

import org.leochen.samples.service.user.CustomUserService;
import org.leochen.samples.web.validation.ChangePasswordValidationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * User: leochen
 * Date: 11-12-7
 * Time: 下午2:37
 */
@Controller
public class UserController {

    @Autowired
    private CustomUserService customUserService;


    @RequestMapping(value = "/sec/users/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(@ModelAttribute("changePasswordValidationBean") ChangePasswordValidationBean changePasswordValidationBean) {
        return "users/changePassword";
    }


    @RequestMapping(value = "/sec/users/changePassword", method = RequestMethod.POST)
    public String processChangePassword(@Valid ChangePasswordValidationBean changePasswordValidationBean, BindingResult binder) {
        if (binder.hasErrors()) {
            return "users/changePassword";
        }

        String oldPassword = changePasswordValidationBean.getOldPassword();
        String newPassword = changePasswordValidationBean.getNewPassword();


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        boolean change = customUserService.changePassword(username, oldPassword, newPassword);

        if (change) {
            return "redirect:/";
        } else {
            FieldError fieldError = new FieldError("changePasswordValidationBean", "oldPassword"
                    , null, false, new String[]{"validation.oldPassword.not.correct"}
                    , null, "Your old password was not correct.");

            binder.addError(fieldError);
            return "users/changePassword";
        }
    }
}
