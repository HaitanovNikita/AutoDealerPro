package autodealer.com.logic.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MachinePartsDao<T> {

      T create(T object);
      T update(T object);
      void delete(T object);
      T find(Long id);
      List<T> read();

}
