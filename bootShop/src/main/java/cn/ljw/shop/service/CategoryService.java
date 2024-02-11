package cn.ljw.shop.service;
import java.util.List;
import cn.ljw.shop.pojo.Category;

/**
 * @author 罗佳维
 * @date 2024/2/1 11:35
 * description
 */
public interface CategoryService {
    //获取类别列表
    List<Category> getCategories(String name);
    //根据类别名获取类别对象
    Category getByName(String name);

    //根据类别id获取类别对象
    Category getById(int id);

    //插入类别
    int addCategory(String name);
    //修改类别
    int editCategory(Category category);

}
