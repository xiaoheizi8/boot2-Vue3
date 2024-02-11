package cn.ljw.shop;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//开启事务
@EnableTransactionManagement
@MapperScan("cn.ljw.shop.mapper")
//缓存异常无法DI开启事务识别?手动开启
//@ComponentScan(basePackages={"cn.ljw.shop.service","cn.ljw.shop.controller"})
//若启动后类加载处理器可mvn lean后再跑 跑一次关闭一次
@SpringBootApplication
public class BootShopApplication  extends SpringBootServletInitializer {
@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(BootShopApplication.class);
}

    public static void main(String[] args) {
        SpringApplication.run(BootShopApplication.class,args);
    }
}
