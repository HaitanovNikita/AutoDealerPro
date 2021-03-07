package autodealer.com.logic.service.impl;

import autodealer.com.logic.dao.TypeCarBodyDao;
import autodealer.com.logic.entity.TypeCarBody;
import autodealer.com.logic.service.TypeCarBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class TypeCarBodyServiceImpl implements TypeCarBodyService {
    @Autowired
    private TypeCarBodyDao typeCarBodyDao;

    @Override
    public TypeCarBody update(TypeCarBody object) {
        return typeCarBodyDao.update(object);
    }

    @Override
    public TypeCarBody findById(Long id) {
        return typeCarBodyDao.find(id);
    }

    @Override
    public TypeCarBody create(TypeCarBody typeCarBody) {
        return typeCarBodyDao.create(typeCarBody);
    }

    @Override
    public void delete(TypeCarBody typeCarBody) {
        typeCarBodyDao.delete(typeCarBody);
    }

    @Override
    public List<TypeCarBody> readAll() {
        return typeCarBodyDao.read();
    }
}
