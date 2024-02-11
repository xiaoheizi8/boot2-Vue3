package cn.ljw.shop.service.impl;

import cn.ljw.shop.mapper.OrderInfoMapper;
import cn.ljw.shop.pojo.OrderDetail;
import cn.ljw.shop.pojo.OrderInfo;
import cn.ljw.shop.service.OrderInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/10 17:39
 * description
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OrderInfoServiceImpl implements OrderInfoService {
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public int addOrder(OrderInfo oi) {
        int res1=1,res2=1;
        //Main List
        res1=orderInfoMapper.insertOrderInfo(oi);
        for (OrderDetail od:oi.getOrderDetails()){
            od.setOid(oi.getId());
            //遍历订单明细
            res2*=orderInfoMapper.insertOrderDetail(od);
        }
        return res1*res2;
    }

    @Override
    public List<OrderInfo> getOrderInfo(ConcurrentHashMap<String, Object> params) {
        return orderInfoMapper.findByPage(params);
    }

    @Override
    public Integer count(ConcurrentHashMap<String, Object> params) {
        return orderInfoMapper.count(params);
    }

    @Override
    public int deleteOrderDetailByOid(int oid) {
        return orderInfoMapper.deleteOrderDetailByOid(oid);
    }

    @Override
    public List<OrderDetail> getOrderDetailByOid(int oid) {
        return orderInfoMapper.findOrderDetailByOid(oid);
    }

    //根据订单id 删除表的明细和表
    @Override
    public int deleteOrderById(int id) {
        int res1=1;
        int res2=1;
        //明细
        res1=orderInfoMapper.deleteOrderDetailByOid(id);
        //order table
        res2=orderInfoMapper.deleteOrderInfoById(id);

        return res1*res2;
    }
}
