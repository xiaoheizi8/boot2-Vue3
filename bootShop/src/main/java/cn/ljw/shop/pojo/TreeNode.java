package cn.ljw.shop.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author 罗佳维
 * @date 2024/1/31 19:02
 * description
 */
@Data
public class TreeNode {
    private int id;//节点
    private String text;//节点名称

    private int pid;//父节点
    private String iconfont;//节点图标字体
    private String url;//跳转路径
    private List<TreeNode> children;//子节点被包含集

}
