package ekb.work.repository;

import java.util.List;

/**
 * Created by juliaskvortsova on 9/21/16.
 */
public interface Repository<T> {

    List<T> findAll(Class<T> typeParameterClass);
    T save(T entity) throws Exception;
    void saveAll(List<T> entities) throws Exception;
    void clearAll(Class<T> typeParameterClass);
}
