package cn.ljw.shop.interceptor;

import cn.ljw.shop.annotation.PassToken;
import cn.ljw.shop.annotation.UserLoginToken;
import cn.ljw.shop.pojo.AdminInfo;
import cn.ljw.shop.service.AdminInfoService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author 罗佳维
 * @date 2024/2/1 13:48
 * description 授权拦截器
 */

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    AdminInfoService adminInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");//从http请求头获取token
        //非URL 映射到方法直接pass
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod) handler;

        Method method=handlerMethod.getMethod();//反射获取方法
        //检查是否有事务有即可跳过认真
        if (method.isAnnotationPresent(PassToken.class)){
            PassToken passToken=method.getAnnotation(PassToken.class);
            if (passToken.required()){
                return true;
            }
        }
        //检查跨域注解的情况下执行认证
        if (method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken=method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()){
                if (token==null){
                    throw  new RuntimeException("没有token,请重新登录");
                }
                //获取token中用户id
                String userId;
                try {
                    userId= JWT.decode(token).getAudience().get(0);//解码0
                }catch (JWTDecodeException e){
                    throw new RuntimeException("401");
                }
                AdminInfo adminInfo=adminInfoService.getAdminById(Integer.parseInt(userId));
                if (adminInfo==null){
                    throw new RuntimeException("该用户不存在，请重新登录");
                }
                JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(adminInfo.getPwd())).build();
                try {
                    jwtVerifier.verify(token);
                }catch (JWTVerificationException e){
                    throw new RuntimeException("401");
                }
                request.setAttribute("currentUser",adminInfo);//当前用户村粗到请求中
                return true;
            }
        }
         return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
     }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
     }
}
