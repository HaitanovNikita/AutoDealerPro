package autodealer.com.logic.service;

import java.util.List;

public interface AutoDealerCRUDService<T> {

    void create(T t);
    void update(T t);
    void delete(T t);
    List<T> readAll();

}
