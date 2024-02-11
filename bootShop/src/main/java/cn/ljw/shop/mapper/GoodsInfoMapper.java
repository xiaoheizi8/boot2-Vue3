package cn.ljw.shop.mapper;

import cn.ljw.shop.pojo.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/1/31 19:39
 * description 货物crud
 */
@Mapper
public interface GoodsInfoMapper {
    //根据状态获取商品列表
    List<GoodsInfo> findGoodsInfoByStatus(int status);
    //获取满足条件的商品总数
    Integer count(ConcurrentHashMap<String,Object> params);
    //分页获取商品总数
    List<GoodsInfo>findByPage(ConcurrentHashMap<String,Object>params);
    //插入商品
    int insertGoodsInfo(GoodsInfo goodsInfo);
    //by id
    GoodsInfo findById(int id);
    int updateGoodsInfo(GoodsInfo goodsInfo);
}
