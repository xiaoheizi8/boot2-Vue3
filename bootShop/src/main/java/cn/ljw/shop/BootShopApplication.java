package cn.ljw.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan(basePackages ={"cn.ljw.shop.mapper"})
@SpringBootApplication
public class BootShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootShopApplication.class,args);
    }
}
