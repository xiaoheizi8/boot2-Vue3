package cn.ljw.shop.pojo;

import lombok.Data;

/**
 * @author 罗佳维
 * @date 2024/1/31 18:58
 * description
 */
@Data
public class Pager {
    private int curPage;//待显示页
    private int perPageRows;//每页显示记录数
    private int rowCount;//记录总数
    private int pageCount;//总页数

    public int getPageCount() {
        return (rowCount + perPageRows - 1) / perPageRows;
    }

    // 分页显示时，获取当前页的第一条记录的索引
    public int getFirstLimitParam() {
        return (this.curPage - 1) * this.perPageRows;
    }
}

