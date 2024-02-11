package cn.ljw.shop.controller;

import cn.ljw.shop.annotation.UserLoginToken;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.*;
import javax.annotation.Resource;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author 罗佳维
 * @date 2024/2/10 15:22
 * description
 */
//利用Redis生成订单号
@RestController
public class SequenceController {
    //初始化为生成订单号做准备
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @UserLoginToken
    @GetMapping("/shop/getSequence")
    public ConcurrentHashMap<String,Object> getSequence(String prefix){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        //Nosql dateTime for orderNumber
        String datetime=new SimpleDateFormat("yyyyMMdd").format(new Date());
        //Nosql Key
        String key= MessageFormat.format("{0}:{1}:{2}","sys",prefix,datetime);
        Long autoIdD=stringRedisTemplate.opsForValue().increment(key,1);
        if (autoIdD==1){
            //设置key的过期时间保证每天的流水从一开始"Setting an expiration time for the key to ensure that daily transactions or activity logs start from the beginning of each day."
            stringRedisTemplate.expire(key,86400, TimeUnit.SECONDS);//精确到秒
        }
        String value= StringUtils.leftPad(String.valueOf(autoIdD),4,"0");
        //🐎 DD开头
        String code = MessageFormat.format("{0}{1}{2}",prefix,datetime,value);
        result.put("code",0);
        result.put("data",code);
        result.put("msg","获取流水号成功");
        return result;
    }
}
