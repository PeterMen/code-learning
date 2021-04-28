package shardingRequestScope;

import lombok.Data;

/**
 * 创建配置类，主要是Environment无法获取全部的key，因此我们绑定一个配置类，进而可以遍历
 */
@Data
public class DbConfigProperties {

    private String dbUrl;

    private String dbUsername;

    private String dbPassword;
}
