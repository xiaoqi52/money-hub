package com.money.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "ruoyi")
public class RuoYiConfig
{

    private String name;

    private String version;

    private String copyrightYear;

    private boolean demoEnabled;

    private static String profile;

    private static boolean addressEnabled;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        this.copyrightYear = copyrightYear;
    }

    public boolean isDemoEnabled()
    {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled)
    {
        this.demoEnabled = demoEnabled;
    }

    public static String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        RuoYiConfig.profile = profile;
    }

    public static boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled)
    {
        RuoYiConfig.addressEnabled = addressEnabled;
    }

    
    public static String getImportPath()
    {
        return getProfile() + "/import";
    }

    
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }
}