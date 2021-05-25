package spring.springBoot.ioc;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AService {

    @Transactional
    public String getUserName(){
        return "张三";
    }
}
