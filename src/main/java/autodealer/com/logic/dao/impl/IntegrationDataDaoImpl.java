package autodealer.com.logic.dao.impl;

import autodealer.com.logic.dao.IntegrationDataDao;
import autodealer.com.logic.entity.IntegrationData;
import autodealer.com.logic.repository.IntegrationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static autodealer.com.logic.utils.Constants.*;

/**
 * @author nhaitanov
 */
@Component
public class IntegrationDataDaoImpl implements IntegrationDataDao {

    private final IntegrationDataRepository integrationDataRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public IntegrationDataDaoImpl(IntegrationDataRepository integrationDataRepository) {
        this.integrationDataRepository = integrationDataRepository;
    }

    @Override
    public List<IntegrationData> readAll() {
        return (ArrayList) integrationDataRepository.findAll();
    }

    @Override
    public IntegrationData findById(Long id) {
        return integrationDataRepository.findById(id).get();
    }

    @Override
    public IntegrationData save(IntegrationData integrationData) {
        return integrationDataRepository.save(integrationData);
    }

    @Override
    public IntegrationData delete(IntegrationData integrationData) {
        integrationDataRepository.delete(integrationData);
        return integrationData;
    }

    @Override
    public List<IntegrationData> findByAnswerIdAndSection(long aid, long section) {
        Query namedQuery = entityManager.createNamedQuery(nameQueryForFindingByAnswerIdAndSection);
        namedQuery.setParameter("aId", aid);
        namedQuery.setParameter("section", section);
        ArrayList<IntegrationData> resultList = (ArrayList<IntegrationData>) namedQuery.getResultList();
        return resultList;
    }

    @Override
    public List<IntegrationData> findByText(String text) {
        Query namedQuery = entityManager.createNamedQuery(nameQueryForFindingByText);
        namedQuery.setParameter("text", text);
        ArrayList<IntegrationData> resultList = (ArrayList<IntegrationData>) namedQuery.getResultList();
        return resultList;
    }

    @Override
    public List<IntegrationData> findBySection(long section) {
        Query namedQuery = entityManager.createNamedQuery(nameQueryForFindingBySection);
        namedQuery.setParameter("section", section);
        ArrayList<IntegrationData> resultList = (ArrayList<IntegrationData>) namedQuery.getResultList();
        return resultList;
    }
}
