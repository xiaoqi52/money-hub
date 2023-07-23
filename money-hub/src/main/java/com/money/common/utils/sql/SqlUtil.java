package com.money.common.utils.sql;

import com.money.common.exception.UtilException;
import com.money.common.utils.StringUtils;


public class SqlUtil
{
    
    public static String SQL_REGEX = "and |extractvalue|updatexml|exec |insert |select |delete |update |drop |count |chr |mid |master |truncate |char |declare |or |+|user()";

    
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";

    
    public static String escapeOrderBySql(String value)
    {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value))
        {
            throw new UtilException("The parameter does not conform to the specification and cannot be queried");
        }
        return value;
    }

    
    public static boolean isValidOrderBySql(String value)
    {
        return value.matches(SQL_PATTERN);
    }

    
    public static void filterKeyword(String value)
    {
        if (StringUtils.isEmpty(value))
        {
            return;
        }
        String[] sqlKeywords = StringUtils.split(SQL_REGEX, "\\|");
        for (String sqlKeyword : sqlKeywords)
        {
            if (StringUtils.indexOfIgnoreCase(value, sqlKeyword) > -1)
            {
                throw new UtilException("Parameters are at risk of SQL injection");
            }
        }
    }
}
