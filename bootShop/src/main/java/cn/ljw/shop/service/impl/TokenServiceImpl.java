package cn.ljw.shop.service.impl;

import cn.ljw.shop.pojo.AdminInfo;
import cn.ljw.shop.service.TokenService;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

/**
 * @author 罗佳维
 * @date 2024/2/1 13:13
 * description
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(AdminInfo adminInfo) {

        String token="";//init
        token=JWT.create().withAudience(String.valueOf(adminInfo.getId())) //将管理员id存储到token
                .sign(Algorithm.HMAC256(adminInfo.getPwd()));//设置秘密为token
        return token;
    }
}
