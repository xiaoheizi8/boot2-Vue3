package cn.ljw.shop.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author 罗佳维
 * @date 2024/2/11 16:28
 * description
 */
@Configuration
public class SpringDocConfig {
    private String title="ljw的电商后台管理系统";
    private String description="关于SpringBoot2+Vue3电商后台管理系统的接口文档";
    private String version="v1.0.0";
    private String websiteName="ljw 后台管理系统";
    private String websiteUrl="http://www.luojiawei.com";
    @Bean
    public OpenAPI heroOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(title)
                        .description(description)
                        .version(version))
                .externalDocs(new ExternalDocumentation().description(websiteName)
                        .url(websiteUrl));
    }
}
