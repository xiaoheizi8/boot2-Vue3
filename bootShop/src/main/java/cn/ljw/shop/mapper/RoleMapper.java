package cn.ljw.shop.mapper;

import cn.ljw.shop.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/1/31 19:46
 * description
 */
@Mapper
public interface RoleMapper {
    //获取所有角色的列表
    List<Role> findAllRole();
    //根据状态获取角色列表
    List<Role> findRoleByDelState(int delState);
    //根据角色id获取对应功能的集合
    List<Integer> findFunctionIdsByRid(int roleId);
    //根据id删除角色功能??
    int deleteFunctionsByRoleId(int roleId);
    int insertFunctionSByRoleId(ConcurrentHashMap<String,Object> params);
    //根据管理员id获取相应角色
    Role findRoleByAdminId(int aid);
    //跟新管理员角色
    int updateRoleByAdminId(ConcurrentHashMap<String,Object>params);
    int insertRole(String roleName);//插入角色
    int updateRole(Role role);
    Role findByRoleId(int roleId);
    int updateDelState(int roleId);
    Role findByRoleName(String roleName);
    int insertAdminRole(ConcurrentHashMap<String,Object>params);

}
