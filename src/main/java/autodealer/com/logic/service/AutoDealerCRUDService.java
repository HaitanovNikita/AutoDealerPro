package autodealer.com.logic.service;

import java.util.List;

public interface AutoDealerCRUDService<T>{

    T save(T t);
    void delete(T t);
    List<T> readAll();

}
