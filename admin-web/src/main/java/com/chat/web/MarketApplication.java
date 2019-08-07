package com.chat.web;

import com.bove.configuration.CommonConfiguration;
import com.bove.configuration.InterceptorConfiguration;
import com.bove.configuration.RedisConfigConfiguration;
import com.bove.framework.util.pager.DatatablePageHelper;
import com.bove.framework.util.redisUtil.ApplicationContextUtil;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@EnableSwagger2
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.chat.web.*.mapper")
@EnableTransactionManagement
//@Import({DatatablePageHelper.class, CommonConfiguration.class, RedisConfigConfiguration.class, InterceptorConfiguration.class, ApplicationContextUtil.class})
@Import({DatatablePageHelper.class, CommonConfiguration.class, RedisConfigConfiguration.class, ApplicationContextUtil.class})
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}

	// 其中 dataSource 框架会自动为我们注入
    @Bean
    @DependsOn("druidDataSource")
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

        // 定义分隔符,配置Swagger多包
    private static final String splitor = ";";

    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 详细定制
                .apiInfo(apiInfo())
                .select()
                // 指定当前包路径，这里就添加了两个包，注意方法变成了basePackage，中间加上成员变量splitor
                .apis(basePackage("com.chat.web"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 添加摘要信息
     * 这里是接口的描述配置，不重要
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                .title("franky聊天室_接口文档")
                .description("用于franky聊天室系统内部开发组生成RESTapi风格的接口...")
//                .contact(new Contact(Global.getName(), null, null))
//                .version("版本号:" + Global.getVersion())
                .build();
    }

    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(splitor)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

}
