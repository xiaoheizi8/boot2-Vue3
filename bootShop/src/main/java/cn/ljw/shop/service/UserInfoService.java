package cn.ljw.shop.service;

import cn.ljw.shop.pojo.UserInfo;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/11 14:43
 * description
 */
public interface UserInfoService {
    //根据状态获取客户列表
    List<UserInfo> getUserInfoByStatus(int status);
    //根据条件查询客户总数
    Integer count(ConcurrentHashMap<String,Object>params);
    //分页获取客户信息
    List<UserInfo>getUserInfos(ConcurrentHashMap<String,Object>params);
    //更新客户状态
    int updateStatus(int id);
    //根据客户id获取客户信息
    UserInfo getUserInfoById(int id);
    //根据客户姓名获取客户信息
    UserInfo getByUserName(String userName);
    //修改客户信息
    int editUserInfo(UserInfo userInfo);

}
