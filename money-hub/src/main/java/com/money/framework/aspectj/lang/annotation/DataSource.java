package com.money.framework.aspectj.lang.annotation;

import com.money.framework.aspectj.lang.enums.DataSourceType;

import java.lang.annotation.*;


@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource
{

    public DataSourceType value() default DataSourceType.MASTER;
}
