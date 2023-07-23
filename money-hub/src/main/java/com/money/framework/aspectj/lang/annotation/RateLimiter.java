package com.money.framework.aspectj.lang.annotation;

import com.money.common.constant.CacheConstants;
import com.money.framework.aspectj.lang.enums.LimitType;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter
{

    public String key() default CacheConstants.RATE_LIMIT_KEY;

    public int time() default 60;

    public int count() default 100;


    public LimitType limitType() default LimitType.DEFAULT;
}
