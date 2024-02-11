package cn.ljw.shop.mapper;

import cn.ljw.shop.pojo.AdminInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/1/31 19:29
 * description
 * 管理员的crud
 */
@Mapper
public interface AdminInfoMapper {
    //根据姓名和密码查询管理员
    AdminInfo findByNameAndPwd(AdminInfo adminInfo);

    AdminInfo findByName(@Param("name") String name);//传入

    AdminInfo findAdminById(@Param("id")int id);
    List<AdminInfo>findAll();
    Integer count(ConcurrentHashMap<String,Object> params);//根据条件查询管理员总数
    //分页获取信息
    List<AdminInfo> findByPage(ConcurrentHashMap<String,Object> params);

    // 插入管理员
    int insertAdminInfo(AdminInfo adminInfo);

    // 修改管理员
    int updateAdminInfo(AdminInfo adminInfo);

    // 更新管理员状态
    int updateDelState(int id);
}
