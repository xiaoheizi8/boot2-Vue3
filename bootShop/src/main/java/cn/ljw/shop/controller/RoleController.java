package cn.ljw.shop.controller;

import cn.ljw.shop.annotation.UserLoginToken;
import cn.ljw.shop.pojo.Role;
import cn.ljw.shop.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/11 15:00
 * description
 */
@RestController
@Slf4j
public class RoleController {
    @Resource
    private RoleService roleService;
    @UserLoginToken
    @GetMapping("/shop/role/valid")
    public ConcurrentHashMap<String,Object>getValidRoles(){
        ConcurrentHashMap<String,Object>result=new ConcurrentHashMap<>();
        List<Role> roles=roleService.getValidRole();
        if(roles.size()>0){
            result.put("code",0);
            result.put("data",roles);
            result.put("msg","获取角色列表成功");
        }else {
            result.put("code",1);
            result.put("msg","获取角色列表失败");
        }
        return result;
    }

    @UserLoginToken
    @GetMapping("/shop/roles")
    public ConcurrentHashMap<String,Object> getAllRoles(){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        List<Role> roles =roleService.getAllRole();
        if(roles.size()>0){
            result.put("code",0);
            result.put("data",roles);
            result.put("msg","获取角色列表成功");
        }else {
            result.put("code",1);
            result.put("msg","获取角色列表失败");
        }
        return result;
    }
        //根据角色id获取叶子接单功能菜单id集合
    @UserLoginToken
    @GetMapping("/shop/getLeafFunctionsByRid")
    public List<Integer> getLeafFunctionsByRid(Integer roleId){
        List<Integer> result= roleService.getFunctionIdsByRid(roleId);
        return result;
    }
    @UserLoginToken
    @PostMapping("/shop/roles/{roleId}")
    public ConcurrentHashMap<String,Object> savePowers(@PathVariable Integer roleId,@RequestBody ConcurrentHashMap params){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        int flag=roleService.bindFunctionsByRoleId(roleId,params.get("fids").toString());
        if(flag>0){
            result.put("code",0);
            result.put("msg","设置权限成功");
        }else {
            result.put("code",1);
            result.put("msg","设置权限失败");
        }
        return result;
    }
    // 根据roleId获取对象
    @UserLoginToken
    @GetMapping("/shop/roles/roleId/{roleId}")
    public ConcurrentHashMap<String,Object> getRoleByRoleId(@PathVariable("roleId") int roleId) {
        ConcurrentHashMap<String,Object> result = new ConcurrentHashMap<>();
        Role role = roleService.getByRoleId(roleId);
        if(role!=null){
            result.put("code",0);
            result.put("msg","获取角色成功！");
            result.put("data",role);
        }else{
            result.put("code",1);
            result.put("msg","获取角色失败！");
        }
        return result;
    }
    // 添加角色
    @UserLoginToken
    @PostMapping("shop/roles")
    public ConcurrentHashMap<String,Object> addRole(@RequestBody Role role){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        int flag = roleService.addRole(role.getRoleName());
        if(flag>0){
            result.put("code",0);
            result.put("msg","添加成功");
        }else {
            result.put("code",1);
            result.put("msg","添加失败");
        }
        return result;
    }
    // 修改角色
    @UserLoginToken
    @PutMapping("/shop/roles")
    public ConcurrentHashMap<String,Object> editRole(@RequestBody Role role){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        int flag = roleService.editRole(role);
        if(flag>0){
            result.put("code",0);
            result.put("msg","修改成功");
        }else {
            result.put("code",1);
            result.put("msg","修改失败");
        }
        return result;
    }
    // 判断角色名是否存在
    @UserLoginToken
    @GetMapping("/shop/roles/roleName/{roleName}")
    public ConcurrentHashMap<String,Object> isExistRoleName(@PathVariable String roleName){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        Role role = roleService.getByRoleName(roleName);
        if(role!=null){
            result.put("code",1);
            result.put("msg","角色名称已存在");
        }else {
            result.put("code",0);
        }
        return result;
    }

    // 判断角色名和角色id是否存在
    @UserLoginToken
    @GetMapping("/shop/roles/roleName/{roleName}/roleId/{roleId}")
    public ConcurrentHashMap<String,Object> isExistRoleName_RoleId(@PathVariable String roleName,@PathVariable int roleId){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        Role role = roleService.getByRoleName(roleName);
        if(role!=null && role.getRoleId()!=roleId ){
            result.put("code",1);
            result.put("msg","角色名称已存在");
        }else {
            result.put("code",0);
        }
        return result;
    }

    // 禁用角色
    @UserLoginToken
    @PutMapping("/shop/roles/roleId/{roleId}/delState")
    public ConcurrentHashMap<String,Object> editRole(@PathVariable int roleId){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        int flag = roleService.updateDelState(roleId);
        if(flag>0){
            result.put("code",0);
            result.put("msg","修改成功");
        }else {
            result.put("code",1);
            result.put("msg","修改失败");
        }
        return result;
    }

}
