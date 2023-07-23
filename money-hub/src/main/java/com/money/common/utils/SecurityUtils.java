package com.money.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.money.common.constant.HttpStatus;
import com.money.common.exception.ServiceException;
import com.money.framework.security.LoginUser;


public class SecurityUtils
{

    
    public static Long getUserId()
    {
        try
        {
            return getLoginUser().getUserId();
        }
        catch (Exception e)
        {
            throw new ServiceException("get user id exception", HttpStatus.UNAUTHORIZED);
        }
    }

    
    public static Long getDeptId()
    {
        try
        {
            return getLoginUser().getDeptId();
        }
        catch (Exception e)
        {
            throw new ServiceException("Obtaining the department ID is abnormal", HttpStatus.UNAUTHORIZED);
        }
    }

    
    public static String getUsername()
    {
        try
        {
            return getLoginUser().getUsername();
        }
        catch (Exception e)
        {
            throw new ServiceException("get user account exception", HttpStatus.UNAUTHORIZED);
        }
    }

    
    public static LoginUser getLoginUser()
    {
        try
        {
            return (LoginUser) getAuthentication().getPrincipal();
        }
        catch (Exception e)
        {
            throw new ServiceException("Obtaining user information exception", HttpStatus.UNAUTHORIZED);
        }
    }

    
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    
    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }
}
