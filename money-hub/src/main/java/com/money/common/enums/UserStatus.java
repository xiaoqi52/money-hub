package com.money.common.enums;


public enum UserStatus
{
    OK("0", "normal"), DISABLE("1", "disabled"), DELETED("2", "delete");

    private final String code;
    private final String info;

    UserStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
