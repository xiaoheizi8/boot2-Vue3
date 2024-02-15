package cn.ljw.shop.controller;

import cn.ljw.shop.annotation.UserLoginToken;
import cn.ljw.shop.pojo.Pager;
import cn.ljw.shop.pojo.UserInfo;
import cn.ljw.shop.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    // 分页获取客户列表
    @UserLoginToken
    @GetMapping("/shop/userinfos")
    public ConcurrentHashMap<String, Object> getUserInfos(Integer curPage, Integer pageSize, UserInfo userInfo) {
        // 创建对象result，保存查询结果数据
        ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();
        // 创建对象params，封装查询条件
        ConcurrentHashMap<String, Object> params = new ConcurrentHashMap<>();
        params.put("userName", userInfo.getUserName());
        // 根据查询条件，获取管记录数
        int totalCount = userInfoService.count(params);
        // 创建分页类对象
        Pager pager = new Pager();
        pager.setCurPage(curPage);
        pager.setPerPageRows(pageSize);
        params.put("pager", pager);
        // 根据查询条件，分页获取管理员列表
        List<UserInfo> userInfos = userInfoService.getUserInfos(params);
        // System.out.println(userInfos.size());
        if (userInfos.size() > 0) {
            result.put("code", 0);
            result.put("page", curPage);
            result.put("total", totalCount);
            result.put("userInfos", userInfos);
            result.put("msg", "获取用户列表成功");
        } else {
            result.put("code", 1);
            result.put("msg", "没有用户记录");
        }
        return result;
    }
    // 禁用用户
    @UserLoginToken
    @PutMapping("/shop/userinfos/id/{id}/status")
    public ConcurrentHashMap<String, Object> editRole(@PathVariable int id) {
        ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();
        int flag = userInfoService.updateStatus(id);
        if (flag > 0) {
            result.put("code", 0);
            result.put("msg", "禁用成功");
        } else {
            result.put("code", 1);
            result.put("msg", "禁用失败");
        }
        return result;
    }
    // 根据客户id获取客户信息
    @UserLoginToken
    @GetMapping("/shop/userinfos/id/{id}")
    public ConcurrentHashMap<String, Object> getUserInfoById(@PathVariable int id) {
        ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();
        UserInfo userInfo = userInfoService.getUserInfoById(id);
        if (userInfo != null) {
            result.put("code", 0);
            result.put("userInfo", userInfo);
        } else {
            result.put("code", 1);
            result.put("msg", "获取用户失败");
        }
        return result;
    }
    // 判断客户名称和id是否存在
    @UserLoginToken
    @GetMapping("/shop/userinfos/userName/{userName}/id/{id}")
    public ConcurrentHashMap<String, Object> isExistAdminInfoName_Id(@PathVariable String userName, @PathVariable int id) {
        ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();
        UserInfo userInfo = userInfoService.getByUserName(userName);
        if (userInfo != null && userInfo.getId() != id) {
            result.put("code", 1);
            result.put("msg", "用户名称已存在");
        } else {
            result.put("code", 0);
        }
        return result;
    }
    // 修改
    @UserLoginToken
    @PutMapping("/shop/userinfos")
    public ConcurrentHashMap<String, Object> editUserInfo(@RequestBody UserInfo userInfo) {
        ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();
        int flag = userInfoService.editUserInfo(userInfo);
        if (flag > 0) {
            result.put("code", 0);
            result.put("msg", "修改成功");
        } else {
            result.put("code", 1);
            result.put("msg", "修改失败");
        }
        return result;
    }

}
