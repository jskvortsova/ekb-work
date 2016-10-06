package ekb.work.repository;

import java.util.List;
import java.util.Map;

/**
 * Created by juliaskvortsova on 9/21/16.
 */
public interface Repository<T> {

    List<T> findAll(Class<T> entityClass);
    T save(T entity);
    void saveAll(List<T> entities) throws Exception;
    void clearAll(Class<T> entityClass);
    List<T> findAll(Map<String, String> params, Class<T> entityClass);
}
