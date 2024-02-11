package cn.ljw.shop.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Ljw
 * 产品实体
 */
@Data
public class Category implements Serializable {
    private int id;
    private String name;//产品类型名字
}
