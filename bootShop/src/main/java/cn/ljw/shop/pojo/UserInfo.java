package cn.ljw.shop.pojo;

import lombok.Data;

/**
 * @author Ljw
 * 客户
 */
@Data
public class UserInfo {
    private int id;
    private String userName;
    private String password;
    private String realName;
    private String sex;
    private String address;
    private String email;
    private String regDate;
    private int status;
}
