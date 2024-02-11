package cn.ljw.shop.mapper;

import cn.ljw.shop.pojo.Functions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 罗佳维
 * @date 2024/1/31 19:37
 * description
 */
@Mapper
public interface FunctionsMapper {
    //根据id获取系统功能对象
    Functions findFunctionsById(int id);
    //获取系统功能列表
    List<Functions> findAllFunctions();
    //编号集合查询
    List<String> findFunctionIdsByAid(int aid);
}
