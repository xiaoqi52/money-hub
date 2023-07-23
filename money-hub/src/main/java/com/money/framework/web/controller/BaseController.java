package com.money.framework.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.money.common.constant.HttpStatus;
import com.money.common.utils.DateUtils;
import com.money.common.utils.PageUtils;
import com.money.common.utils.SecurityUtils;
import com.money.common.utils.StringUtils;
import com.money.common.utils.sql.SqlUtil;
import com.money.framework.security.LoginUser;
import com.money.framework.web.domain.AjaxResult;
import com.money.framework.web.page.PageDomain;
import com.money.framework.web.page.TableDataInfo;
import com.money.framework.web.page.TableSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;


public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }


    protected void startPage()
    {
        PageUtils.startPage();
    }


    protected void startOrderBy()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy()))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }


    protected void clearPage()
    {
        PageUtils.clearPage();
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("search successful");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }


    public AjaxResult success()
    {
        return AjaxResult.success();
    }


    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    public AjaxResult success(Object data)
    {
        return AjaxResult.success(data);
    }


    public AjaxResult error()
    {
        return AjaxResult.error();
    }


    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    public AjaxResult warn(String message)
    {
        return AjaxResult.warn(message);
    }


    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }


    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }


    public LoginUser getLoginUser()
    {
        return SecurityUtils.getLoginUser();
    }


    public Long getUserId()
    {
        return getLoginUser().getUserId();
    }


    public Long getDeptId()
    {
        return getLoginUser().getDeptId();
    }

    public String getUsername()
    {
        return getLoginUser().getUsername();
    }
}
