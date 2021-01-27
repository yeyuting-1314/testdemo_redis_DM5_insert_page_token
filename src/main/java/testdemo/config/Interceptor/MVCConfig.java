package testdemo.config.Interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import testdemo.util.interceptor.AuthenticationInterceptor;


@Configuration
public class MVCConfig implements WebMvcConfigurer {


    @Bean
    public HandlerInterceptor authenticationInterceptor(){

        return new AuthenticationInterceptor();
    }
    /*
    * 静态资源映射
    * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/") ;
    }
    @Override
    public void addInterceptors (InterceptorRegistry registry){
       registry.addInterceptor(authenticationInterceptor())
                //表示拦截所有请求
                .addPathPatterns("/*")
                //表示取消对特定路径的拦截
                .excludePathPatterns("/login")
                .excludePathPatterns("/loginCheck");
                //.excludePathPatterns("/loginWithRedis")
                //这里一定不要写成/**/*.js的形式，spring boot无法识别
                //取消对static目录下静态资源的拦截
               // .excludePathPatterns("/static/**") ;
    }




}
