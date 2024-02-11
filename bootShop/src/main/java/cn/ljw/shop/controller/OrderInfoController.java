package cn.ljw.shop.controller;

import cn.ljw.shop.annotation.UserLoginToken;
import cn.ljw.shop.pojo.OrderDetail;
import cn.ljw.shop.pojo.OrderInfo;
import cn.ljw.shop.pojo.Pager;
import cn.ljw.shop.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
/**
 * @author 罗佳维
 * @date 2024/2/10 17:58
 * description
 *
 */
@RestController
public class OrderInfoController {
    @Resource
    private OrderInfoService orderInfoService;
    @UserLoginToken
    @GetMapping("/shop/ordersinfos")
    public ConcurrentHashMap<String,Object>getOrderInfos(OrderInfo orderInfo,Integer curPage,Integer pageSize){
        //封装结果
        ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
        //query参数
        ConcurrentHashMap<String,Object>params=new ConcurrentHashMap<>();
        params.put("orderNo",orderInfo.getOrderNo());
        params.put("uid",orderInfo.getUid());
        params.put("status",orderInfo.getStatus());
        // 获取符合条件的订单总数
        int totalCount=orderInfoService.count(params);
        //创建分页对象
        Pager pager=new Pager();
        pager.setCurPage(curPage);
        pager.setPerPageRows(pageSize);
        params.put("pager",pager);
        //获取当前页的记录
        List<OrderInfo> orderInfos=orderInfoService.getOrderInfo(params);
        if(orderInfos.size()>0){
            result.put("code",0);
            result.put("orderInfos",orderInfos);
            result.put("total",totalCount);
            result.put("page",curPage);
            result.put("msg","获取订单列表成功");
        }else {
            result.put("code",1);
            result.put("msg","获取订单列表失败");
        }
        return result;

    }
    //根据订单id获取订单明细列表
    @UserLoginToken
    @GetMapping("/shop/orderinfos/oid/{oid}")
    public ConcurrentHashMap<String,Object>getOrderDetailByOid(@PathVariable("oid")int oid){
        ConcurrentHashMap<String,Object> res=new ConcurrentHashMap<>();
        List<OrderDetail>orderDetails=orderInfoService.getOrderDetailByOid(oid);
        if(orderDetails!=null &&orderDetails.size()>0){
            res.put("code",0);
            res.put("orderDetails",orderDetails);
            res.put("msg","获取订单明细数据成");
        }else {
            res.put("code",1);
            res.put("msg","获取订单明细数据失败");
        }
        return res;
    }
    //根据订单id删除订单
    @UserLoginToken
    @DeleteMapping("/shop/orderinfos/{id}")
    public ConcurrentHashMap<String,Object>deleteOrderInfo(@PathVariable String id){
        ConcurrentHashMap<String,Object> res=new ConcurrentHashMap<>();
        int flag=orderInfoService.deleteOrderById(Integer.parseInt(id));
        if (flag>0){
            res.put("code",0);
            res.put("msg","删除订单成功");
        }
        else {
            res.put("code",1);
            res.put("msg","删除订单失败");
        }
        return res;
    }
    //提交表单
    @UserLoginToken
    @PostMapping("/shop/commitOrder")
    public ConcurrentHashMap<String,Object>commitOrder(@RequestBody OrderInfo orderInfo){
        ConcurrentHashMap<String,Object>result=new ConcurrentHashMap<>();
        String str1 = "";
        String ordertime = orderInfo.getOrdertime();
        ordertime = ordertime.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date time = format.parse(ordertime);
            str1 = defaultFormat.format(time);
            log.info("str1 is {}", str1); // 使用log.info代替System.out.println
        } catch (Exception e) {
            log.error("An error occurred while parsing the order time", e); // 使用log.error并记录异常
        }
        orderInfo.setOrdertime(str1);
        try {
            int flag = orderInfoService.addOrder(orderInfo);
            if (flag > 0) {
                result.put("code", 0);
                result.put("msg", "创建订单成功");
                log.info("Order created successfully"); // 成功创建订单的日志信息
            } else {
                result.put("code", 1);
                result.put("msg", "创建订单失败");
                log.warn("Failed to create order"); // 失败时记录警告日志
            }
        } catch (Exception e) {
            result.put("code", 1);
            result.put("msg", "创建订单失败");
            log.error("An error occurred while creating the order", e); // 记录创建订单时的异常
        }
        return result;
    }


}

