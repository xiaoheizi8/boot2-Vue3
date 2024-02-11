package cn.ljw.shop.pojo;

import lombok.Data;

/**
 * @author Ljw
 * 2024.1.31
 * 角色
 */
@Data
public class Role {
    private int roleId;
    private String roleName;
    private int delState;//状态
}
