package cn.ljw.shop.service;

import cn.ljw.shop.pojo.AdminInfo;

/**
 * @author 罗佳维
 * @date 2024/2/1 13:12
 * description Token效验
 */
public interface TokenService {
    String getToken(AdminInfo admin);
}
