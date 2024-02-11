package cn.ljw.shop.service.impl;

import cn.ljw.shop.mapper.FunctionsMapper;
import cn.ljw.shop.mapper.RoleMapper;
import cn.ljw.shop.pojo.Functions;
import cn.ljw.shop.pojo.Role;
import cn.ljw.shop.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/1 13:26
 * description
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)

public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private FunctionsMapper functionsMapper;
    @Override
    public List<Role> getAllRole() {
        return roleMapper.findAllRole();
    }

    @Override
    public List<Role> getValidRole() {
        return roleMapper.findRoleByDelState(0);//有效为0
    }

    @Override
    public List<Integer> getFunctionIdsByRid(int roleId) {
        List<Integer> res=new ArrayList();
        List<Integer> fids=roleMapper.findFunctionIdsByRid(roleId);
        if ((fids!=null)&&(fids.size()>0)){
            for (int i = 0; i < fids.size(); i++) {
                Functions functions=functionsMapper.findFunctionsById(Integer.parseInt(fids.get(i).toString()));
                if (functions.isIsleaf()){
                    res.add(functions.getId());//add 得到的id
                }
            }
        }return res;
     }

    //根据角色id绑定功能
    @Override
    public int bindFunctionsByRoleId(int roleId, String fids) {
        String[] fidStrings=fids.split(",");
        int res1=1,res2=1;
        List<Integer> ids=roleMapper.findFunctionIdsByRid(roleId);
        if (ids.size()>0){
            res1=roleMapper.deleteFunctionsByRoleId(roleId);
        }
        ConcurrentHashMap<String,Object> params=new ConcurrentHashMap<>();
        params.put("rid",roleId);
        params.put("fid",1);
        res2 *=roleMapper.insertFunctionSByRoleId(params);//key value
        for (int i = 0; i <fidStrings.length ; i++) {
            params=new ConcurrentHashMap<>();
            params.put("rid",roleId);
            params.put("fid",fidStrings[i]);
            res2*=roleMapper.insertFunctionSByRoleId(params);



        }
        return res1*res2;
    }

    @Override
    public Role getRoleByAdminId(int aid) {
        return roleMapper.findRoleByAdminId(aid);
    }

    @Override
    public int updateRoleByAdminId(ConcurrentHashMap<String, Object> params) {
        return roleMapper.updateRoleByAdminId(params);
    }

    @Override
    public int addRole(String roleName) {
        return roleMapper.insertRole(roleName);
    }

    @Override
    public int editRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public Role getByRoleName(String roleName) {
        return  roleMapper.findByRoleName(roleName);
    }

    @Override
    public Role getByRoleId(int roleId) {
        return roleMapper.findByRoleId(roleId);
    }

    @Override
    public int updateDelState(int roleId) {
        return roleMapper.updateDelState(roleId);
    }
}
