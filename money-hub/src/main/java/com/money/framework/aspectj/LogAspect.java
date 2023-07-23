package com.money.framework.aspectj;

import com.alibaba.fastjson2.JSON;
import com.money.common.filter.PropertyPreExcludeFilter;
import com.money.common.utils.SecurityUtils;
import com.money.common.utils.StringUtils;
import com.money.framework.aspectj.lang.annotation.Log;
import com.money.framework.security.LoginUser;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

@Aspect
@Component
public class LogAspect
{
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    public static final String[] EXCLUDE_PROPERTIES = { "password", "oldPassword", "newPassword", "confirmPassword" };

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("Cost Time");

    @Before(value = "@annotation(controllerLog)")
    public void boBefore(JoinPoint joinPoint, Log controllerLog)
    {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult)
    {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e)
    {
        handleLog(joinPoint, controllerLog, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, Log controllerLog, final Exception e, Object jsonResult)
    {
        try
        {
            LoginUser loginUser = SecurityUtils.getLoginUser();

            //SysOperLog operLog = new SysOperLog();
            //operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            //String ip = IpUtils.getIpAddr();
            //operLog.setOperIp(ip);
            //operLog.setOperUrl(StringUtils.substring(ServletUtils.getRequest().getRequestURI(), 0, 255));
            //if (loginUser != null)
            //{
            //    operLog.setOperName(loginUser.getUsername());
            //}
            //
            //if (e != null)
            //{
            //    operLog.setStatus(BusinessStatus.FAIL.ordinal());
            //    operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            //}
            //String className = joinPoint.getTarget().getClass().getName();
            //String methodName = joinPoint.getSignature().getName();
            //operLog.setMethod(className + "." + methodName + "()");
            //operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            //getControllerMethodDescription(joinPoint, controllerLog, operLog, jsonResult);
            //operLog.setCostTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());
            //AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
        }
        catch (Exception exp)
        {
            log.error("error:{}", exp.getMessage());
            exp.printStackTrace();
        }
        finally
        {
            TIME_THREADLOCAL.remove();
        }
    }

    //public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysOperLog operLog, Object jsonResult) throws Exception
    //{
    //    operLog.setBusinessType(log.businessType().ordinal());
    //    operLog.setTitle(log.title());
    //    operLog.setOperatorType(log.operatorType().ordinal());
    //    if (log.isSaveRequestData())
    //    {
    //        setRequestValue(joinPoint, operLog, log.excludeParamNames());
    //    }
    //    if (log.isSaveResponseData() && StringUtils.isNotNull(jsonResult))
    //    {
    //        operLog.setJsonResult(StringUtils.substring(JSON.toJSONString(jsonResult), 0, 2000));
    //    }
    //}

    //private void setRequestValue(JoinPoint joinPoint, SysOperLog operLog, String[] excludeParamNames) throws Exception
    //{
    //    String requestMethod = operLog.getRequestMethod();
    //    Map<?, ?> paramsMap = ServletUtils.getParamMap(ServletUtils.getRequest());
    //    if (StringUtils.isEmpty(paramsMap)
    //            && (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)))
    //    {
    //        String params = argsArrayToString(joinPoint.getArgs(), excludeParamNames);
    //        operLog.setOperParam(StringUtils.substring(params, 0, 2000));
    //    }
    //    else
    //    {
    //        operLog.setOperParam(StringUtils.substring(JSON.toJSONString(paramsMap, excludePropertyPreFilter(excludeParamNames)), 0, 2000));
    //    }
    //}

    private String argsArrayToString(Object[] paramsArray, String[] excludeParamNames)
    {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0)
        {
            for (Object o : paramsArray)
            {
                if (StringUtils.isNotNull(o) && !isFilterObject(o))
                {
                    try
                    {
                        String jsonObj = JSON.toJSONString(o, excludePropertyPreFilter(excludeParamNames));
                        params += jsonObj.toString() + " ";
                    }
                    catch (Exception e)
                    {
                    }
                }
            }
        }
        return params.trim();
    }
    public PropertyPreExcludeFilter excludePropertyPreFilter(String[] excludeParamNames)
    {
        return new PropertyPreExcludeFilter().addExcludes(ArrayUtils.addAll(EXCLUDE_PROPERTIES, excludeParamNames));
    }


    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o)
    {
        Class<?> clazz = o.getClass();
        if (clazz.isArray())
        {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        else if (Collection.class.isAssignableFrom(clazz))
        {
            Collection collection = (Collection) o;
            for (Object value : collection)
            {
                return value instanceof MultipartFile;
            }
        }
        else if (Map.class.isAssignableFrom(clazz))
        {
            Map map = (Map) o;
            for (Object value : map.entrySet())
            {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }
}
