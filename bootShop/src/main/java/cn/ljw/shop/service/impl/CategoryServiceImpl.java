package cn.ljw.shop.service.impl;

import cn.ljw.shop.mapper.CategoryMapper;
import cn.ljw.shop.pojo.Category;
import cn.ljw.shop.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 罗佳维
 * @date 2024/2/1 13:09
 * description
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> getCategories(String name) {
        return categoryMapper.findCategories(name);
    }

    @Override
    public Category getByName(String name) {
        return categoryMapper.findByName(name);
    }

    @Override
    public Category getById(int id) {
        return categoryMapper.findById(id);
    }

    @Override
    public int addCategory(String name) {
        return categoryMapper.insertCategory(name);
    }

    @Override
    public int editCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }
}
