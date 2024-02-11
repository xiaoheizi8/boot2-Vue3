package cn.ljw.shop.controller;

import cn.ljw.shop.annotation.UserLoginToken;
import cn.ljw.shop.pojo.Functions;
import cn.ljw.shop.pojo.TreeNode;
import cn.ljw.shop.service.FunctionsService;
import cn.ljw.shop.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/11 14:54
 * description
 */
@Slf4j
@RestController
public class FunctionsController {
    @Resource
    private FunctionsService functionsService;;
    @UserLoginToken
    @GetMapping("/shop/functions")
    public ConcurrentHashMap<String,Object>getFunctions(){;
        log.info("获取功能");
        ConcurrentHashMap<String,Object>result=new ConcurrentHashMap<>();
        List<Functions>functionsList=functionsService.getAllFunctions();
        List<TreeNode> nodes=new ArrayList<>();
        Collections.sort(functionsList);
        for(Functions functions:functionsList){
            TreeNode treeNode=new TreeNode();
            treeNode.setIconfont(functions.getIconfont());//图标
            treeNode.setId(functions.getId());//id
            treeNode.setText(functions.getName());//name
            treeNode.setPid(functions.getParentid());//Pid
            treeNode.setUrl(functions.getUrl());//URL mapping
            nodes.add(treeNode);
        }
        List<TreeNode>treeNodes= JsonUtil.buildTree(nodes,0);
        result.put("code",0);
        result.put("data",treeNodes);
        result.put("msg","获取所有功能菜单成功");
        return result;
    }
}
