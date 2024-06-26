package cn.ljw.shop.controller;

import cn.ljw.shop.annotation.UserLoginToken;
import cn.ljw.shop.pojo.AdminInfo;
import cn.ljw.shop.pojo.Functions;
import cn.ljw.shop.pojo.Pager;
import cn.ljw.shop.pojo.TreeNode;
import cn.ljw.shop.service.AdminInfoService;
import cn.ljw.shop.service.RoleService;
import cn.ljw.shop.service.TokenService;
import cn.ljw.shop.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/1 14:25
 * description 用户接口
 */
@Slf4j
//@CrossOrigin
@RestController
//@RequestMapping("/shop")
public class AdminInfoController {
    @Resource
    private AdminInfoService adminInfoService;
    @Resource
    private TokenService tokenService;
    @Resource
    private RoleService roleService;
    //用户名和密码合法效验
    @PostMapping("/shop/login")
    public ConcurrentHashMap<String,Object>login(@RequestBody AdminInfo adminInfo){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        AdminInfo ai=adminInfoService.getByName(adminInfo);//根据名字来找
        if (ai!=null){
            //判断密码是否正确
            if (!ai.getPwd().equals(adminInfo.getPwd())){
                result.put("code",2);//放入key value
                result.put("msg","登陆失败,密码错误");
            }else {
                //登录成功 生成token
                String token= tokenService.getToken(ai);
                result.put("code",0);
                result.put("token",token);
                result.put("admin",ai);            }
        }
        else {
            result.put("code",1);
            result.put("msg","登录失败用户不存在");
        }
        return result;
    }
    //获取登录用户的功能列表
    @UserLoginToken
    @GetMapping("/shop/menus")
    public ConcurrentHashMap<String,Object> getMenus(HttpServletRequest request){
        ConcurrentHashMap<String,Object> res=new ConcurrentHashMap<>();
        List<TreeNode> nodes=new ArrayList<>();
        //获取登录管理员对象
        AdminInfo adminInfo= (AdminInfo) request.getAttribute("currentUser");
        List<Functions> functionsList=adminInfoService.getFunctionsByAdminId(adminInfo.getId());
        //对list的功能泛型类型对象集合排序
        Collections.sort(functionsList);
        for (Functions functions:functionsList){
            TreeNode treeNode=new TreeNode();
            treeNode.setIconfont(functions.getIconfont());
            treeNode.setId(functions.getId());
            treeNode.setPid(functions.getParentid());
            treeNode.setText(functions.getName());
            treeNode.setUrl(functions.getUrl());
            nodes.add(treeNode);
        }
        //给nodes列表各个ThreeNode元素的子节点赋值
        List<TreeNode> treeNodes= JsonUtil.buildTree(nodes,0);
        res.put("code",0);
        res.put("msg","获取菜单成功");
        log.info("获取菜单成功");
        res.put("data",treeNodes);
        return res;

    }    @UserLoginToken
    @GetMapping("/shop/admininfos")
    public ConcurrentHashMap<String,Object> getAdmininfos(AdminInfo adminInfo,Integer curPage,Integer pageSize){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        ConcurrentHashMap<String,Object> params=new ConcurrentHashMap<>();
        params.put("name",adminInfo.getName());
        int totalCount=adminInfoService.count(params);
        Pager pager=new Pager();
        pager.setCurPage(curPage);
        pager.setPerPageRows(pageSize);
        params.put("pager",pager);
        List<AdminInfo> adminInfos=adminInfoService.getAdminInfo(params);
        if(adminInfos.size()>0){
            result.put("code",0);
            result.put("adminInfos",adminInfos);
            result.put("page",curPage);
            result.put("total",totalCount);
            result.put("msg","获取管理员列表成功");
        }else {
            result.put("code",1);
            result.put("msg","没有管理员记录");
        }
        return result;
    }

    // 根据管理员id获取管理员信息
    @UserLoginToken
    @GetMapping("/shop/admininfos/id/{id}")
    public ConcurrentHashMap<String,Object> getAdminInfoById(@PathVariable int id){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        AdminInfo adminInfo = adminInfoService.getAdminById(id);
        if(adminInfo!=null){
            result.put("code",0);
            result.put("adminInfo",adminInfo);
        }else {
            result.put("code",1);
            result.put("msg","获取管理员失败");
        }
        return result;
    }
    // 修改管理员信息
    @UserLoginToken
    @PutMapping("/shop/admininfos")
    public ConcurrentHashMap<String,Object> editAdminInfo(@RequestBody AdminInfo adminInfo){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        int flag = adminInfoService.editAdminInfo(adminInfo);
        if(flag>0){
            result.put("code",0);
            result.put("msg","修改成功");
        }else {
            result.put("code",1);
            result.put("msg","修改失败");
        }
        return result;
    }

    //分配角色
        @UserLoginToken
    @PutMapping("/shop/admininfos/{adminId}/role/{roleId}")
    public ConcurrentHashMap<String,Object> saveRole(@PathVariable("adminId")Integer adminId,@PathVariable("roleId")Integer roleId){
        ConcurrentHashMap<String,Object>res=new ConcurrentHashMap<>();
        ConcurrentHashMap<String,Object>params=new ConcurrentHashMap<>();
            params.put("roleId",roleId);
            params.put("adminId",adminId);
            if (roleService.updateRoleByAdminId(params)>0){
                //参数
                res.put("code",0);//正常
                res.put("msg","分配角色成功!");
            }else {
                res.put("code",1);
                res.put("msg","分配角色失败");
            }
            return  res;
        }
        @UserLoginToken
    @PostMapping("/shop/admininfos")
    public ConcurrentHashMap<String,Object> addAdminInfo(@RequestBody AdminInfo adminInfo){
            ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
            int flag = adminInfoService.saveAdminInfo(adminInfo);
            if(flag>0){
                result.put("code",0);
                result.put("msg","添加成功");
            }else {
                result.put("code",1);
                result.put("msg","添加失败");
            }
            return result;
        }
        @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
        //判断管理员是否存在
       @UserLoginToken
    @GetMapping("/shop/admininfos/name/{name}")
    public ConcurrentHashMap<String,Object> isExistAdminInfoName(@PathVariable String name){
    ConcurrentHashMap<String,Object>result=new ConcurrentHashMap<>();
           AdminInfo ai=new AdminInfo();
           ai.setName(name);
           AdminInfo adminInfo = adminInfoService.getByName(ai);
           if(adminInfo!=null){
               result.put("code",1);
               result.put("msg","管理员名称已存在");
           }else {
               result.put("code",0);
           }
           return result;
        }
    // 禁用管理员
    @UserLoginToken
    @PutMapping("/shop/admininfos/id/{id}/delState")
    public ConcurrentHashMap<String,Object> disableAdminInfo(@PathVariable int id){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        int flag = adminInfoService.updateDelState(id);
        if(flag>0){
            result.put("code",0);
            result.put("msg","修改成功");
        }else {
            result.put("code",1);
            result.put("msg","修改失败");
        }
        return result;
    }
    // 判断管理员名和id是否存在
    @UserLoginToken
    @GetMapping("/shop/admininfos/name/{name}/id/{id}")
    public ConcurrentHashMap<String,Object> isExistAdminInfoName_Id(@PathVariable String name,@PathVariable int id){
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        AdminInfo ai=new AdminInfo();
        ai.setName(name);
        AdminInfo adminInfo = adminInfoService.getByName(ai);
        if(adminInfo!=null && adminInfo.getId()!=id ){
            result.put("code",1);
            result.put("msg","管理员名称已存在");
        }else {
            result.put("code",0);
        }
        return result;
    }
}
