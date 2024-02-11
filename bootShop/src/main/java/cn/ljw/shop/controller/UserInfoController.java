package cn.ljw.shop.controller;

import cn.ljw.shop.annotation.UserLoginToken;
import cn.ljw.shop.pojo.UserInfo;
import cn.ljw.shop.service.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/11 15:07
 * description
 */
@RestController
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;
    @UserLoginToken
    @GetMapping("/shop/customers")
    public ConcurrentHashMap<String,Object>getCustomers(int status){
       ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        List<UserInfo> userInfos =userInfoService.getUserInfoByStatus(status);
        if(userInfos.size()>0){
            result.put("code",0);
            result.put("data",userInfos);
            result.put("msg","获取客户列表成功");
        }else {
            result.put("code",1);
            result.put("msg","获取客户列表失败");
        }
        return result;
    }
    }
