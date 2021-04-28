package spring.springBoot.ioc;

import org.springframework.stereotype.Service;

@Service
public class AService {

    public String getUserName(){
        return "张三";
    }
}
