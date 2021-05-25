package spring.springBoot.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringBootStrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStrap.class);
    }
}
