package hello.login;

import hello.login.web.filter.Logfilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LogInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");
    }

    //@Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> fiterResgistrationBean = new FilterRegistrationBean<>();
        fiterResgistrationBean.setFilter(new Logfilter());
        fiterResgistrationBean.setOrder(1);
        fiterResgistrationBean.addUrlPatterns("/*");

        return fiterResgistrationBean;
    }

    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> fiterResgistrationBean = new FilterRegistrationBean<>();
        fiterResgistrationBean.setFilter(new LoginCheckFilter());
        fiterResgistrationBean.setOrder(2);
        fiterResgistrationBean.addUrlPatterns("/*");

        return fiterResgistrationBean;
    }
}
