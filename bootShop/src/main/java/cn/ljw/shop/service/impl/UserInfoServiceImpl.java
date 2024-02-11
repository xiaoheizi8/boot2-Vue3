package cn.ljw.shop.service.impl;

import cn.ljw.shop.mapper.UserInfoMapper;
import cn.ljw.shop.pojo.UserInfo;
import cn.ljw.shop.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/11 14:48
 * description 用户业务逻辑
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;
    //根据状态获取客户列表
    @Override
    public List<UserInfo> getUserInfoByStatus(int status) {
        return userInfoMapper.findUserInfoByStatus(status);
    }

    @Override
    public Integer count(ConcurrentHashMap<String, Object> params) {
        return userInfoMapper.count(params);
    }

    @Override
    public List<UserInfo> getUserInfos(ConcurrentHashMap<String, Object> params) {
        return userInfoMapper.findByPage(params);
    }

    @Override
    public int updateStatus(int id) {
        return userInfoMapper.updateStatus(id);
    }

    @Override
    public UserInfo getUserInfoById(int id) {
        return userInfoMapper.findUserInfoById(id);
    }

    @Override
    public UserInfo getByUserName(String userName) {
        return userInfoMapper.findUserInfoByUserName(userName);
    }

    @Override
    public int editUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }
}
