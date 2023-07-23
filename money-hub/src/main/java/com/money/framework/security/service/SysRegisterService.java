package com.money.framework.security.service;

import com.money.common.constant.CacheConstants;
import com.money.common.constant.Constants;
import com.money.common.constant.UserConstants;
import com.money.common.exception.user.CaptchaException;
import com.money.common.exception.user.CaptchaExpireException;
import com.money.common.utils.MessageUtils;
import com.money.common.utils.SecurityUtils;
import com.money.common.utils.StringUtils;
import com.money.framework.manager.AsyncManager;
import com.money.framework.manager.factory.AsyncFactory;
import com.money.framework.redis.RedisCache;
import com.money.framework.security.RegisterBody;
import com.money.project.system.domain.SysUser;
import com.money.project.system.service.ISysConfigService;
import com.money.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    
    public String register(RegisterBody registerBody)
    {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);


        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled)
        {
            validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
        }

        if (StringUtils.isEmpty(username))
        {
            msg = "User name cannot be empty";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "The user password cannot be empty";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "The account length must be between 2 and 20 characters";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "The password length must be between 5 and 20 characters";
        }
        else if (!userService.checkUserNameUnique(sysUser))
        {
            msg = "Failed to save user" + username + "registered account already exists";
        }
        else
        {
            sysUser.setNickName(username);
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag)
            {
                msg = "Registration failed. Please contact the system administrator";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }
}
