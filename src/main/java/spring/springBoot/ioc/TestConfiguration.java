package spring.springBoot.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.cglibproxy.UserLogin;

@Configuration
public class TestConfiguration {

    @Bean
    public UserLogin userLogin(){
        return new UserLogin();
    }

    @Bean
    public TestFactoryBean testFactoryBean(){
        return new TestFactoryBean();
    }
}
