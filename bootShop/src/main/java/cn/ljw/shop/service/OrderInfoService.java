package cn.ljw.shop.service;

import cn.ljw.shop.pojo.OrderDetail;
import cn.ljw.shop.pojo.OrderInfo;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/10 15:39
 * description
 */
//订单接口
public interface OrderInfoService {
    //add
    int addOrder(OrderInfo oi);
    //分页获取订单列表
    List<OrderInfo> getOrderInfo(ConcurrentHashMap<String,Object>params);
    //获取满足条件的订单总数
    Integer count(ConcurrentHashMap<String,Object>params);
    //根据订单id删除订单明细
    int deleteOrderDetailByOid(int oid);
    //根据订单id获取订单明细列表
    List<OrderDetail> getOrderDetailByOid(int oid);
    //根据订单id删除订单记录和订单明细记录
    int deleteOrderById(int id);


}
