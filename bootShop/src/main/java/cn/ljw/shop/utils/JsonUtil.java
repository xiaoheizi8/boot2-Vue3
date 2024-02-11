package cn.ljw.shop.utils;

import cn.ljw.shop.pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 罗佳维
 * @date 2024/1/31 20:04
 * description Json转换器轮子
 */

public final class JsonUtil {
    public static List<TreeNode> buildTree(List<TreeNode>nodes,int id){
        List<TreeNode> treeNodes=new ArrayList<TreeNode>();//init for last
        for (TreeNode treeNode:nodes){
            TreeNode node=new TreeNode();
            node.setId(treeNode.getId());
            node.setText(treeNode.getText());
            node.setIconfont(treeNode.getIconfont());
            node.setPid(treeNode.getPid());
            node.setUrl(treeNode.getUrl());
            if (id==treeNode.getPid()){
                //递归调用buildTree给TreeNode中children属性赋值
                node.setChildren(buildTree(nodes,node.getId()));
            treeNodes.add(node);
            }
        }
        return treeNodes;

    }
}
