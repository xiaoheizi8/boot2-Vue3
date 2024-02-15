package cn.ljw.shop.controller;

import cn.ljw.shop.annotation.UserLoginToken;
import cn.ljw.shop.pojo.Category;
import cn.ljw.shop.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/2/10 11:15
 * description
 **/
@Slf4j
@RestController
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    //根据类别名称获取类别列表
    @UserLoginToken
    @GetMapping("/shop/categories")
    public ConcurrentHashMap<String, Object> getCategories(String name) {
//        Logger logger = LoggerFactory.getLogger(getClass());
        ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();

        try {
            List<Category> categories = categoryService.getCategories(name);
            if (categories.size() > 0) {
                result.put("msg", "获取商品列表成功");
                result.put("categories", categories);
                result.put("code", 0);
            } else {
                result.put("msg", "获取商品列表失败");
                result.put("code", 1);
            }
        } catch (Exception e) {
            // 记录异常日志
            log.error("获取商品类别时发生异常", e);
            // 可以在这里处理异常，比如返回错误信息
            result.put("msg", "系统异常");
            result.put("code", 500);
        }

        return result;
    }
    // 判断类别名称是否存在
    @UserLoginToken
    @GetMapping("/shop/categories/name/{name}")
    public ConcurrentHashMap<String, Object> isExistCategoryName(@PathVariable String name) {
        ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();
        Category category = categoryService.getByName(name);
        if (category != null) {
            result.put("code", 1);
            result.put("msg", "商品类别名称已存在");
        } else {
            result.put("code", 0);
        }
        return result;
    }
    // 判断类别名称和id是否存在
    @UserLoginToken
    @GetMapping("/shop/categories/name/{name}/id/{id}")
    public ConcurrentHashMap<String, Object> isExistCategoryName_Id(@PathVariable String name, @PathVariable int id) {
        ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();
        Category category = categoryService.getByName(name);
        if (category != null && category.getId() != id) {
            result.put("code", 1);
            result.put("msg", "商品类别名称已存在");
        } else {
            result.put("code", 0);
        }
        return result;
    }
    // 根据类别id获取类别信息
    @UserLoginToken
    @GetMapping("/shop/categories/id/{id}")
    public ConcurrentHashMap<String, Object> getCategoryById(@PathVariable int id) {
        ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();
        Category category = categoryService.getById(id);
        if (category != null) {
            result.put("code", 0);
            result.put("category", category);
        } else {
            result.put("code", 1);
            result.put("msg", "获取商品类别失败");
        }
        return result;
    }
    // 添加类别
    @UserLoginToken
    @PostMapping("/shop/categories")
    public ConcurrentHashMap<String, Object> addCategory(@RequestBody Category category) {
        ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();
        int flag = categoryService.addCategory(category.getName());
        if (flag > 0) {
            result.put("code", 0);
            result.put("msg", "添加成功");
        } else {
            result.put("code", 1);
            result.put("msg", "添加失败");
        }
        return result;
    }
    //修改商品类别
    @UserLoginToken
    @PutMapping("/shop/categories")
    public ConcurrentHashMap<String, Object> editCategory(@RequestBody Category category) {
        ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<>();
        int flag = categoryService.editCategory(category);
        if (flag > 0) {
            result.put("code", 0);
            result.put("msg", "修改成功");
        } else {
            result.put("code", 1);
            result.put("msg", "修改失败");
        }
        return result;
    }
    }

