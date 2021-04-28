package shardingRequestScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestAttributes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class DbTemplateConfig {

    @Autowired
    private Environment env;

    @Autowired
    private ApplicationContext context;

    @Scope(value = RequestAttributes.REFERENCE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Bean
    @Lazy
    @Primary
    public DbTemplate dbTemplate() throws InvocationTargetException, IllegalAccessException {

        Set<String> fieldNames = Arrays.stream(DbConfigProperties.class.getDeclaredFields()).map(Field::getName).collect(Collectors.toSet());

        String tenant = TenantThreadLocal.getTenant();
        String propertyPrefix;
        String beanPrefix;
        if ("GET".equals(tenant)) {
            propertyPrefix = "a.db.";
            beanPrefix = "a";
        } else {
            propertyPrefix = "b.db.";
            beanPrefix = "b";
        }
        String beanName = beanPrefix + "DbTemplate";
        if (!context.containsBean(beanName)) {
            register(fieldNames, propertyPrefix, beanName);
        }

        return context.getBean(beanName, DbTemplate.class);

    }

    private void register(Set<String> fieldNames, String propertyPrefix, String beanName) throws IllegalAccessException, InvocationTargetException {
        DbConfigProperties dbConfig = buildDbConfig(fieldNames, propertyPrefix);

        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DbTemplateImpl.class);
        beanDefinitionBuilder.addPropertyValue("dbConfigProperties", dbConfig);
        defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }

    private DbConfigProperties buildDbConfig(Set<String> fieldNames, String propertyPrefix) throws IllegalAccessException, InvocationTargetException {
        DbConfigProperties dbConfig = new DbConfigProperties();
        for (String fieldName : fieldNames) {
            String key = propertyPrefix + fieldName;
            if (!env.containsProperty(key)) {
                throw new IllegalArgumentException("输入错误");
            }
            String property = env.getProperty(key);
            Arrays.stream(dbConfig.getClass().getMethods())
                    .filter(m -> m.getName().equals("set" + (fieldName.toCharArray()[0] + "").toUpperCase() + fieldName.substring(1)))
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("字段出错"))
                    .invoke(dbConfig, property);

        }
        return dbConfig;
    }

}
