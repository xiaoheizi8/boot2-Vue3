package cn.ljw.shop.service;

import cn.ljw.shop.pojo.AdminInfo;
import cn.ljw.shop.pojo.Functions;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/1/31 20:38
 * description
 */
public interface AdminInfoService {
    AdminInfo login(AdminInfo ai);//登录验证
    AdminInfo getByName(AdminInfo ai);//根据姓名查询管理员
    AdminInfo getAdminById(int id);//根据id查询管理员
    List<AdminInfo> getAll();//查询所有管理员列表
    //根据条件统计总数
    Integer count(ConcurrentHashMap<String,Object> params);
    //分页获取管理员列表
    List<AdminInfo> getAdminInfo(ConcurrentHashMap<String,Object>params);

    List<Functions> getFunctionsByAdminId(int adminId);
    // 插入管理员
    int saveAdminInfo(AdminInfo adminInfo);

    // 修改管理员
    int editAdminInfo(AdminInfo adminInfo);

    // 更新管理员状态，禁用管理员
    int updateDelState(int id);
}
