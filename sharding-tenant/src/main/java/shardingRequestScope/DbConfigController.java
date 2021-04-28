package shardingRequestScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DbConfigController {

    @Autowired
    private DbTemplate dbTemplate;

    @GetMapping("/api/dbget")
    public String dbget(@RequestParam("id") String id) {
        return dbTemplate.queryById(id);
    }

    @PostMapping("/api/dbpost")
    public String dbpost(@RequestParam("id") String id) {
        return dbTemplate.queryById(id);
    }

}
