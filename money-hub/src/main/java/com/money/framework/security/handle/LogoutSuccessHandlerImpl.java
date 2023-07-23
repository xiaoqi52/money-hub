package com.money.framework.security.handle;

import com.alibaba.fastjson2.JSON;
import com.money.common.constant.Constants;
import com.money.common.utils.ServletUtils;
import com.money.common.utils.StringUtils;
import com.money.framework.manager.AsyncManager;
import com.money.framework.manager.factory.AsyncFactory;
import com.money.framework.security.LoginUser;
import com.money.framework.security.service.TokenService;
import com.money.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenService tokenService;


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            tokenService.delLoginUser(loginUser.getToken());
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "exit successfully"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.success("exit successfully")));
    }
}
