package cn.ljw.shop.mapper;

import cn.ljw.shop.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 罗佳维
 * @date 2024/1/31 19:35
 * description
 */
@Mapper
public interface CategoryMapper {
    //根据类名获取类别列表
    List<Category> findCategories(String name);
    // 插入类别
    int insertCategory(String name);

    // 根据类别名获取类别对象
    Category findByName(String name);

    // 根据类别id获取类别对象
    Category findById(int id);

    // 修改类别
    int updateCategory(Category category);
}
