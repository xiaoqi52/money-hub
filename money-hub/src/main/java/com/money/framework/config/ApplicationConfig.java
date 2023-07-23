package com.money.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.TimeZone;

/**
 * Program Annotation Configuration
 *
 * @author Jingsi Yu
 */
@Configuration
// Indicates that the proxy object is exposed through the aop framework, and AopContext can access it
@EnableAspectJAutoProxy(exposeProxy = true)
// Specifies the path to the package of Mapper classes to scan
@MapperScan("com.money.project.**.mapper")
public class ApplicationConfig
{
    /**
     * time zone configuration
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization()
    {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }
}
