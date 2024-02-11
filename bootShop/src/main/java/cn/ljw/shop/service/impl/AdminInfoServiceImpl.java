package cn.ljw.shop.service.impl;

import cn.ljw.shop.mapper.AdminInfoMapper;
import cn.ljw.shop.mapper.FunctionsMapper;
import cn.ljw.shop.mapper.RoleMapper;
import cn.ljw.shop.pojo.AdminInfo;
import cn.ljw.shop.pojo.Functions;
import cn.ljw.shop.service.AdminInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 罗佳维
 * @date 2024/1/31 20:42
 * description
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)//事务
public class AdminInfoServiceImpl implements AdminInfoService {
    @Resource
    private AdminInfoMapper adminInfoMapper;
    @Resource
    private FunctionsMapper functionsMapper;
    @Resource
    private RoleMapper roleMapper;
    @Override
    public AdminInfo login(AdminInfo ai) {
        return adminInfoMapper.findByNameAndPwd(ai);
    }

    @Override
    public AdminInfo getByName(AdminInfo ai) {
        return adminInfoMapper.findByName(ai.getName());
    }

    @Override
    public AdminInfo getAdminById(int id) {
        return adminInfoMapper.findAdminById(id);
    }

    @Override
    public List<AdminInfo> getAll() {
        return adminInfoMapper.findAll();
    }

    @Override
    public Integer count(ConcurrentHashMap<String, Object> params) {
        return adminInfoMapper.count(params);
    }

    @Override
    public List<AdminInfo> getAdminInfo(ConcurrentHashMap<String, Object> params) {
        return adminInfoMapper.findByPage(params);
    }

    @Override
    public List<Functions> getFunctionsByAdminId(int adminId) {
        List<String> fids=functionsMapper.findFunctionIdsByAid(adminId);
        List<Functions> functionsList=new ArrayList<>();//以数组形式
        if (fids!=null &&fids.size()>0){
            for (int i=0;i<fids.size();i++){
                Functions functions=functionsMapper.findFunctionsById(Integer.parseInt(fids.get(i)));//转id
                functionsList.add(functions);
            }
        }
        return functionsList;
    }

    @Override
    public int saveAdminInfo(AdminInfo adminInfo) {
        int result1,result2=1;
        result1=adminInfoMapper.insertAdminInfo(adminInfo);
        ConcurrentHashMap<String,Object> params=new ConcurrentHashMap<>();
        params.put("adminId",adminInfo.getId());//存储给哈希
        params.put("roleId",0);
        result2=roleMapper.insertAdminRole(params);
        return  result1*result2;

    }

    @Override
    public int editAdminInfo(AdminInfo adminInfo) {
        return adminInfoMapper.updateAdminInfo(adminInfo);
    }

    @Override
    public int updateDelState(int id) {
        return adminInfoMapper.updateDelState(id);
    }
}
