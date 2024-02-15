package cn.ljw.shop.controller;

import cn.ljw.shop.annotation.UserLoginToken;
import cn.ljw.shop.mapper.GoodsInfoMapper;
import cn.ljw.shop.pojo.GoodsInfo;
import cn.ljw.shop.pojo.Pager;
import cn.ljw.shop.service.GoodsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/9 15:48
 * description 货物接口
 */
@Slf4j
@RestController
public class GoodsInfoController {
    @Resource
//    private GoodsInfoMapper goodsInfoMapper;
    private GoodsInfoService goodsInfoService;

    //分页获取商品列表
    @UserLoginToken
    @GetMapping("/shop/goods")
    //考虑线程安全问题设计Current
    public ConcurrentHashMap<String,Object> getGoodsInfos(Integer curPage, Integer pageSize, GoodsInfo goodsInfo){
        log.info("访问商品列表");
        ConcurrentHashMap<String,Object>result=new ConcurrentHashMap<>();
        ConcurrentHashMap<String,Object> params=new ConcurrentHashMap<>();
        params.put("name",goodsInfo.getName());
        int totalCount= goodsInfoService.count(params);
        Pager pager=new Pager();
        pager.setCurPage(curPage);
        pager.setPerPageRows(pageSize);
        params.put("pager",pager);
        List<GoodsInfo>goodsInfos=goodsInfoService.getGoodsInfo(params);
        if (goodsInfos.size()>0){
            //启动分页 封装参数
            result.put("code",0);
            result.put("page",curPage);
            result.put("total",totalCount);
            result.put("goodsInfos",goodsInfos);
            result.put("msg","获取商品列表成功");
            log.info("获取商品信息:{}",curPage,totalCount,goodsInfos);
        }
        else {
            result.put("code",1);
            result.put("msg","没有商品记录");
//            log.warn("");
        }
        return result;
    }//添加商品
    @UserLoginToken
    @PostMapping("/shop/goods")
    public ConcurrentHashMap<String,Object> addGoods(@RequestBody GoodsInfo goodsInfo){
        ConcurrentHashMap<String,Object> result = new ConcurrentHashMap<>();
        int flag=goodsInfoService.addGoodsInfo(goodsInfo);
        if(flag>0){
            result.put("code",0);
            result.put("msg","添加成功");
        }else {
            result.put("code",1);
            result.put("msg","添加失败");
        }
        return result;
    }
    // 根据商品id获取商品信息
    @UserLoginToken
    @GetMapping("/shop/goods/id/{id}")
    public ConcurrentHashMap<String,Object> getGoodInfoById(@PathVariable int id){
        ConcurrentHashMap<String,Object> result = new ConcurrentHashMap<>();
        GoodsInfo goodsInfo=goodsInfoService.getById(id);
        if(goodsInfo!=null){
            result.put("code",0);
            result.put("goodsInfo",goodsInfo);
            result.put("msg","获取商品成功");
        }else {
            result.put("code",1);
            result.put("msg","获取商品失败");
        }
        return result;
    }
    // 修改商品
    @UserLoginToken
    @PutMapping("/shop/goods")
    public ConcurrentHashMap<String,Object> editGoods(@RequestBody GoodsInfo goodsInfo){
        ConcurrentHashMap<String,Object> result = new ConcurrentHashMap<>();
        int flag=goodsInfoService.editGoodsInfo(goodsInfo);
        if(flag>0){
            result.put("code",0);
            result.put("msg","修改成功");
        }else {
            result.put("code",1);
            result.put("msg","修改失败");
        }
        return result;
    }
    // 获取商品列表
    @UserLoginToken
    @GetMapping("/shop/goods/status")
    public ConcurrentHashMap<String,Object> getValidGoodsInfo(){
        ConcurrentHashMap<String,Object> result = new ConcurrentHashMap<>();
        List<GoodsInfo> goodsInfos=goodsInfoService.getValidGoodsInfo();
        if(goodsInfos.size()>0){
            result.put("code",0);
            result.put("data",goodsInfos);
            result.put("msg","获取商品列表成功");
        }else {
            result.put("code",1);
            result.put("msg","获取商品列表失败");
        }
        return result;
    }

}
