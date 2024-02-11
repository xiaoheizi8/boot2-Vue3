package cn.ljw.shop.service;

import cn.ljw.shop.pojo.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/1 13:19
 * description 角色业务crud
 */

public interface RoleService {
    List<Role> getAllRole();
    //有效的用户列表
    List<Role> getValidRole();
    //通过id获取功能列表
    List <Integer> getFunctionIdsByRid(int roleId);
    @Transactional
    int bindFunctionsByRoleId(int roleId,String fids);
    Role getRoleByAdminId(int aid);
    //更新管理员角色
    int updateRoleByAdminId(ConcurrentHashMap<String,Object>params);
    //添加角色
    int addRole(String roleName);
    //修改角色
    int editRole(Role role);
    //根据名称获取角色对象
    Role getByRoleName(String roleName);
    //根据角色id获取角色
    Role getByRoleId(int roleId);
    //修改角色状态
    int updateDelState(int roleId);



}
