package shardingRequestScope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbTemplateImpl implements DbTemplate {

    private DbConfigProperties dbConfigProperties;

    @Override
    public String queryById(String id) {
        return dbConfigProperties + ", dao: " + id;
    }
}
