package autodealer.com.logic.service.impl;

import autodealer.com.logic.dao.IntegrationDataDao;
import autodealer.com.logic.dao.converter.Converter;
import autodealer.com.logic.dto.IntegrationDataDTO;
import autodealer.com.logic.service.IntegrationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nhaitanov
 */
@Service
@Transactional
public class IntegrationDataServiceImpl implements IntegrationDataService {

    private final IntegrationDataDao integrationDataDao;

    @Autowired
    public IntegrationDataServiceImpl(IntegrationDataDao integrationDataDao) {
        this.integrationDataDao = integrationDataDao;
    }

    @Override
    public List<IntegrationDataDTO> readAll() {
        return integrationDataDao.readAll().stream().map(integrationData -> Converter.convertEntityToDto(integrationData)).collect(Collectors.toList());
    }

    @Override
    public IntegrationDataDTO findById(Long id) {
        return Converter.convertEntityToDto(integrationDataDao.findById(id));
    }

    @Override
    public IntegrationDataDTO save(IntegrationDataDTO integrationData) {
        return Converter.convertEntityToDto(integrationDataDao.save(Converter.convertDtoToEntity(integrationData)));
    }

    @Override
    public IntegrationDataDTO delete(IntegrationDataDTO integrationData) {
        return Converter.convertEntityToDto(integrationDataDao.delete(Converter.convertDtoToEntity(integrationData)));
    }

    @Override
    public List<IntegrationDataDTO> findByAnswerIdAndSection(long aid, long section) {
        List<IntegrationDataDTO> integrationDataDTOS = integrationDataDao.findByAnswerIdAndSection(aid, section).stream().map(integrationData -> Converter.convertEntityToDto(integrationData)).collect(Collectors.toList());
        return integrationDataDTOS;
    }

    @Override
    public List<IntegrationDataDTO> findByText(String text) {
        List<IntegrationDataDTO> integrationDataDTOS = integrationDataDao.findByText(text).stream().map(integrationData -> Converter.convertEntityToDto(integrationData)).collect(Collectors.toList());
        return integrationDataDTOS;
    }

    @Override
    public List<IntegrationDataDTO> findBySection(long section) {
        List<IntegrationDataDTO> integrationDataDTOS = integrationDataDao.findBySection(section).stream().map(integrationData -> Converter.convertEntityToDto(integrationData)).collect(Collectors.toList());
        return integrationDataDTOS;
    }
}
