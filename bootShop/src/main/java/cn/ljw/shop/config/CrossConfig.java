package cn.ljw.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 罗佳维
 * @date 2024/1/31 19:27
 * description跨域配置
 */
@Configuration
public class CrossConfig  implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("*")
                .allowedMethods("*").maxAge(18000).allowedOrigins("http://localhost:8080");//运行前端跨域
    }
}
