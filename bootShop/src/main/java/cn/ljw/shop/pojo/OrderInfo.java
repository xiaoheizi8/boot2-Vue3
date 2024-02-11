package cn.ljw.shop.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author 罗佳维
 * @date 2024/1/31 19:20
 * description
 */
@Data
public class OrderInfo {
    private Integer id;
    private String orderNo;
    private int uid;
    private UserInfo ui;
    private String status;
    private String ordertime;
    private double orderprice;
    private String orderTimeFrom;
    private String orderTimeTo;
    private List<OrderDetail> orderDetails;
}

