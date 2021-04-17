package autodealer.com.logic.service;

import autodealer.com.logic.dto.IntegrationDataDTO;

import java.util.List;

/**
 * @author nhaitanov
 */
public interface IntegrationDataService {

    List<IntegrationDataDTO> readAll();

    IntegrationDataDTO findById(Long id);

    IntegrationDataDTO save(IntegrationDataDTO integrationData);

    IntegrationDataDTO delete(IntegrationDataDTO integrationData);

    List<IntegrationDataDTO> findByAnswerIdAndSection(long aid, long section);

    List<IntegrationDataDTO> findByText(String text);

    List<IntegrationDataDTO> findBySection(long section);

}
