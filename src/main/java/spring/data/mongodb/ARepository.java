package spring.data.mongodb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.support.Repositories;

import javax.annotation.Resource;
import java.util.List;

public interface ARepository<AEntity, String> extends Repository<AEntity, String> {

    public List<AEntity> findByUserName();
    public long countByUserName();
    public long deleteByUserName();
}
