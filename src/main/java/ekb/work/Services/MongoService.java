package ekb.work.Services;

import java.util.List;
import java.util.Map;

/**
 * Created by juliaskvortsova on 9/10/16.
 */
public interface MongoService {
    List<Object> findAll();
    void saveAll();
    Object findBy(Map<String, String> params);

}
