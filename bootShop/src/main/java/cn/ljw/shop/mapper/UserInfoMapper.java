package cn.ljw.shop.mapper;

import cn.ljw.shop.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/1/31 19:58
 * description
 */
@Mapper
public interface UserInfoMapper {
    //根据状态获取客户列表
    List<UserInfo> findUserInfoByStatus(int status);
    //根据客户id 获取客户信息
    UserInfo findUserInfoById(int id);
    //根据条件查询客户总数
    Integer count(ConcurrentHashMap<String,Object>params);
    //分页获取客户信息
    List<UserInfo> findByPage(ConcurrentHashMap<String,Object> params);

    int updateStatus(int id);
    //靠客户端用户名获取客户信息
    UserInfo findUserInfoByUserName(String userName);
    //修改用户信息
    int updateUserInfo(UserInfo userInfo);

}
