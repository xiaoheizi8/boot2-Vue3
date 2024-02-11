package cn.ljw.shop.pojo;

import lombok.Data;

/**
 * @author 罗佳维
 * @date 2024/1/31 19:06
 * description
 */
@Data
public class Functions implements Comparable<Functions>{
    private int id;
    private String name;
    private int parentid;
    private boolean isleaf;
    private String iconfont;
    private String url;

    @Override
    public int compareTo(Functions functions) {
        return ((Integer) this.getId()).compareTo((Integer)(functions.getId()));
    }
}
