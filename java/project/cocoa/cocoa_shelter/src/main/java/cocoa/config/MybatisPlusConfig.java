package cocoa.config;

//import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
//import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for MyBatis Plus.
 *
 * This class sets up the necessary configurations for using MyBatis Plus,
 * including enabling the pagination plugin for handling paginated database queries.
 */
@Configuration
@MapperScan("cocoa.mapper")
public class MybatisPlusConfig {
    /**
     * Configures and registers the MyBatis Plus interceptor.
     *
     * The interceptor includes a pagination inner interceptor to handle
     * pagination logic automatically during query execution.
     *
     * @return An instance of {@link MybatisPlusInterceptor} with the pagination interceptor added.
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}