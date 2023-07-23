package com.money;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Start program
 * 
 * @author Jingsi Yu
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MoneyHubApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(MoneyHubApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ Money Hub system started successfully ！ ლ(´ڡ`ლ)ﾞ）");
    }



}
