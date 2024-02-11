package cn.ljw.shop.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 罗佳维
 * @date 2024/1/31 19:20
 * description 订单详细表
 */
@Data
public class OrderDetail implements Serializable {
    private int id;
    private int oid;
    private OrderInfo oi;
    private int gid;
    private String code;
    private GoodsInfo goods;
    private int quantity;
    private double price;
    private double totalprice;
}
