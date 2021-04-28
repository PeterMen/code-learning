package sharding.postProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class ShardingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if(registry.containsBeanDefinition("mongoAutoConfiguration")){
            BeanDefinition mongoAutoConf = registry.getBeanDefinition("mongoAutoConfiguration");

//            registry.registerBeanDefinition();
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
