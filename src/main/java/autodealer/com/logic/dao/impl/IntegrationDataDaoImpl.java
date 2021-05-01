package autodealer.com.logic.dao.impl;

import autodealer.com.logic.dao.IntegrationDataDao;
import autodealer.com.logic.dao.converter.Converter;
import autodealer.com.logic.dto.IntegrationDataDTO;
import autodealer.com.logic.entity.IntegrationData;
import autodealer.com.logic.repository.IntegrationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static autodealer.com.logic.utils.SqlConstants.*;

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
    public List<IntegrationDataDTO> readAll() {
        List<IntegrationDataDTO> integrationDataDTOList = new ArrayList<>();
        integrationDataRepository.findAll().forEach(integrationData -> integrationDataDTOList.add(Converter.convertEntityToDto(integrationData)));
        return integrationDataDTOList;
    }

    @Override
    public IntegrationDataDTO findById(Long id) {
        return Converter.convertEntityToDto(integrationDataRepository.findById(id).get());
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
    public List<IntegrationDataDTO> findByAnswerIdAndSection(long aid, long section) {
        Query namedQuery = entityManager.createNamedQuery(nameQueryForFindingByAnswerIdAndSection);
        namedQuery.setParameter("aId", aid);
        namedQuery.setParameter("section", section);
        ArrayList<IntegrationDataDTO> resultList = (ArrayList<IntegrationDataDTO>) namedQuery.getResultList();
        return resultList;
    }

    @Override
    public List<IntegrationDataDTO> findByText(String text) {
        Query namedQuery = entityManager.createNamedQuery(nameQueryForFindingByText);
        namedQuery.setParameter("text", text);
        ArrayList<IntegrationDataDTO> resultList = (ArrayList<IntegrationDataDTO>) namedQuery.getResultList();
        return resultList;
    }

    @Override
    public List<IntegrationDataDTO> findBySection(long section) {
//        Query namedQuery = entityManager.createNamedQuery(nameQueryForFindingBySection);
//        namedQuery.setParameter("section", section);
//        ArrayList<IntegrationDataDTO> resultList = (ArrayList<IntegrationDataDTO>) namedQuery.getResultList();
        List<IntegrationDataDTO> resultList = new ArrayList<>();
        integrationDataRepository.findAllBySection(section).forEach(integrationData -> resultList.add(Converter.convertEntityToDto(integrationData)));
        return resultList;
    }
}
