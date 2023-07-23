package com.money.project.tool.gen.util;

import com.money.common.constant.Constants;
import org.apache.velocity.app.Velocity;

import java.util.Properties;


public class VelocityInitializer
{
    
    public static void initVelocity()
    {
        Properties p = new Properties();
        try
        {

            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

            p.setProperty(Velocity.INPUT_ENCODING, Constants.UTF8);

            Velocity.init(p);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
