package cn.ljw.shop.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 罗佳维
 * @date 2024/1/31 18:53
 * description 商品信息表
 */
@Data
public class GoodsInfo implements Serializable {

    private int id;
    private String code;//商品编码
    private String name;
    private int cid;

    private Category category;//商品类型Object
    private String brand;//商品品牌
    private String pic; //商品图片
    private int num;
    private double price;//商品价格
    private String intro;
    private int status;
    private double priceFrom;
    private double priceTo;


}
