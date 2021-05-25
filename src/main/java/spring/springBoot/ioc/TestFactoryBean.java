package spring.springBoot.ioc;

import org.springframework.beans.factory.FactoryBean;


public class TestFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    class User{}
}
