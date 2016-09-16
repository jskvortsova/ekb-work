package ekb.work.Services;

import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by juliaskvortsova on 9/10/16.
 */
public class MongoServiceImpl implements MongoService {
    private MongoTemplate mongoTemplate;

    public List<Object> findAll() {
        return null;
    }

    @Override
    public void saveAll() {

    }

    @Override
    public Object findBy(Map<String, String> params) {
        return null;
    }
}
