package cn.ljw.shop.mapper;

import cn.ljw.shop.pojo.OrderDetail;
import cn.ljw.shop.pojo.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/1/31 19:45
 * description
 */
@Mapper
public interface OrderInfoMapper {
    // 插入订单
    int insertOrderInfo(OrderInfo oi);

    // 插入订单明细
    int insertOrderDetail(OrderDetail od);

    // 获取满足条件的订单总数
    Integer count(ConcurrentHashMap<String, Object> params);

    // 分页获取订单列表
    List<OrderInfo> findByPage(ConcurrentHashMap<String, Object> params);

    // 根据订单id删除订单明细
    int deleteOrderDetailByOid(int oid);

    // 根据订单id删除订单
    int deleteOrderInfoById(int id);

    // 根据订单id获取订单明细列表
    List<OrderDetail> findOrderDetailByOid(int oid);
}
