package cn.itsource;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
@MapperScan("cn.itsource.mapper")
public class PoductServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PoductServerApplication.class, args);
    }
}
