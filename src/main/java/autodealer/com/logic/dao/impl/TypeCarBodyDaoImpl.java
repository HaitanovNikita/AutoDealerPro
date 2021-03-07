package autodealer.com.logic.dao.impl;

import autodealer.com.logic.dao.TypeCarBodyDao;
import autodealer.com.logic.entity.TypeCarBody;
import autodealer.com.logic.repository.TypeCarBodyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nhaitanov
 */
@Component
public class TypeCarBodyDaoImpl implements TypeCarBodyDao {

    @Autowired
    private TypeCarBodyRepository repository;

    @Override
    public TypeCarBody create(TypeCarBody object) {
        return repository.save(object);
    }

    @Override
    public TypeCarBody update(TypeCarBody object) {
        return repository.save(object);
    }

    @Override
    public void delete(TypeCarBody object) {
         repository.delete(object);
    }

    @Override
    public TypeCarBody find(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<TypeCarBody> read() {
        return (ArrayList<TypeCarBody>) repository.findAll();
    }
}
