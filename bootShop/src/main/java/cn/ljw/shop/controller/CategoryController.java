package cn.ljw.shop.controller;

import cn.ljw.shop.annotation.UserLoginToken;
import cn.ljw.shop.pojo.Category;
import cn.ljw.shop.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
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
    }

