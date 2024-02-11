package cn.ljw.shop.service.impl;

import cn.ljw.shop.mapper.FunctionsMapper;
import cn.ljw.shop.pojo.Functions;
import cn.ljw.shop.service.FunctionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 罗佳维
 * @date 2024/2/11 14:41
 * description 功能业务实现1
 */
@Service
public class FunctionsServiceImpl implements FunctionsService {
@Resource
private FunctionsMapper functionsMapper;
    @Override
    public List<Functions> getAllFunctions() {
        return functionsMapper.findAllFunctions();
    }
}
