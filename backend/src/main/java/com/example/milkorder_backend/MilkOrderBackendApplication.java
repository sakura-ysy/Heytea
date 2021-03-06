package com.example.milkorder_backend;

import com.example.milkorder_backend.jwt.JwtAuthenticationFilter;
import com.example.milkorder_backend.threads.OrderFinishThread;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@MapperScan("com.example.milkorder_backend.mapper") // 该包下的每个接口自动生成实现类
@SpringBootApplication
public class MilkOrderBackendApplication extends SpringBootServletInitializer {
    @Override  // 重写 configure 固定格式
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MilkOrderBackendApplication.class) ;
    }

    // jwt过滤器
    // JavaBean
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(); // 过滤器对象
        registrationBean.setFilter(filter);  // 过滤器注册
        return registrationBean;
    }

    /**
     * 写完咯，
     * 哦吼吼吼吼吼吼
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MilkOrderBackendApplication.class, args);
        // 模拟队列的新线程
        OrderFinishThread orderFinishThread = new OrderFinishThread();
        Thread thread1 = new Thread(orderFinishThread);
        thread1.start();
    }

}
