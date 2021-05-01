package autodealer.com.logic.dao;

import autodealer.com.logic.dto.IntegrationDataDTO;
import autodealer.com.logic.entity.IntegrationData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nhaitanov
 */
@Repository
public interface IntegrationDataDao {

    List<IntegrationDataDTO> readAll();

    IntegrationDataDTO findById(Long id);

    IntegrationData save(IntegrationData integrationData);
    IntegrationData delete(IntegrationData integrationData);

    List<IntegrationDataDTO> findByAnswerIdAndSection(long aid,long section);
    List<IntegrationDataDTO> findByText(String text);
    List<IntegrationDataDTO> findBySection(long section);


}
