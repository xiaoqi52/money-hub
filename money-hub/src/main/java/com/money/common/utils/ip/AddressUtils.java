package com.money.common.utils.ip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.money.common.constant.Constants;
import com.money.common.utils.StringUtils;
import com.money.common.utils.http.HttpUtils;
import com.money.framework.config.RuoYiConfig;


public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);


    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";


    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip)
    {
        // 内网不查询
        if (IpUtils.internalIp(ip))
        {
            return "IP";
        }
        if (RuoYiConfig.isAddressEnabled())
        {
            try
            {
                String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
                if (StringUtils.isEmpty(rspStr))
                {

                    return UNKNOWN;
                }
                JSONObject obj = JSON.parseObject(rspStr);
                String region = obj.getString("pro");
                String city = obj.getString("city");
                return String.format("%s %s", region, city);
            }
            catch (Exception e)
            {
                log.error(" {}", ip);
            }
        }
        return UNKNOWN;
    }
}
