package cn.ljw.shop.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ljw
 * 2024.1.31
 * 管理员
 */
@Data
public class AdminInfo implements Serializable {
    private int id;
    private String name;
    private String pwd;
    private int delState;//根据此状态判断是否启用 0启用,1禁用
    //关联属性调优
    List<Functions> fs;
    private Role role;

}
