package cn.ljw.shop.service;

import cn.ljw.shop.pojo.GoodsInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/9 15:55
 * description
 */
public interface GoodsInfoService {
    //获取有效的商品列表
    List<GoodsInfo> getValidGoodsInfo();
    //获取满足条件1的商品总数
    Integer count(ConcurrentHashMap<String,Object> params);
    //分页获取商品列表
    List<GoodsInfo> getGoodsInfo(ConcurrentHashMap<String,Object>params);
    //add
    int addGoodsInfo(GoodsInfo goodsInfo);
    //根据商品id获取商品信息
    GoodsInfo getById(int id);
    //change
    int editGoodsInfo(GoodsInfo goodsInfo);


}
