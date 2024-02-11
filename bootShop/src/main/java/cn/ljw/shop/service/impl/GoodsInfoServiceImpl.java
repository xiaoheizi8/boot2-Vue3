package cn.ljw.shop.service.impl;

import cn.ljw.shop.mapper.GoodsInfoMapper;
import cn.ljw.shop.pojo.GoodsInfo;
import cn.ljw.shop.service.GoodsInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/9 16:06
 * description
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Resource
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public List<GoodsInfo> getValidGoodsInfo() {
        return goodsInfoMapper.findGoodsInfoByStatus(1);//效验id=1
    }

    @Override
    public Integer count(ConcurrentHashMap<String, Object> params) {
        return goodsInfoMapper.count(params);
    }

    @Override
    public List<GoodsInfo> getGoodsInfo(ConcurrentHashMap<String, Object> params) {
        return goodsInfoMapper.findByPage(params);
    }

    @Override
    public int addGoodsInfo(GoodsInfo goodsInfo) {
        return goodsInfoMapper.insertGoodsInfo(goodsInfo);
    }

    @Override
    public GoodsInfo getById(int id) {
        return goodsInfoMapper.findById(id);
    }

    @Override
    public int editGoodsInfo(GoodsInfo goodsInfo) {
        return goodsInfoMapper.updateGoodsInfo(goodsInfo);
    }
}
