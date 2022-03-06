package ltd.chuchen.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("ltd.chuchen.mapper")  //扫描 Mapper 包
@EnableTransactionManagement  //自动管理事物
@Configuration
public class MyBatisPlusConfig {
    //注册乐观锁插件 ,这是官方写的，相当于是一个拦截器
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    //MyBatisPlus 的分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    //逻辑删除组件
    @Bean
    public ISqlInjector sqlInjector () {
        return new LogicSqlInjector();
    }

    //效率测试插件
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(1000); //ms 设置sql执行的最大时间，若超时则不执行，这个在工作中一般都要设置
        performanceInterceptor.setFormat(true); //是否开启格式化支持，就是在日志给我们看的时候有没有格式
        return performanceInterceptor;
    }
}
