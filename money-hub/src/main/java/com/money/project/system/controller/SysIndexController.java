package com.money.project.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.money.common.utils.StringUtils;
import com.money.framework.config.RuoYiConfig;


@RestController
public class SysIndexController
{

    @Autowired
    private RuoYiConfig ruoyiConfig;


    @RequestMapping("/")
    public String index()
    {
        return StringUtils.format("Welcome to {} background management framework, current version: v{}, please visit through the front-end address。", ruoyiConfig.getName(), ruoyiConfig.getVersion());
    }
}
