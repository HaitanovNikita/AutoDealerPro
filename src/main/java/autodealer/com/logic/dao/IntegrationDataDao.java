package autodealer.com.logic.dao;

import autodealer.com.logic.entity.IntegrationData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nhaitanov
 */
@Repository
public interface IntegrationDataDao {

    List<IntegrationData> readAll();

    IntegrationData findById(Long id);

    IntegrationData save(IntegrationData integrationData);
    IntegrationData delete(IntegrationData integrationData);

    List<IntegrationData> findByAnswerIdAndSection(long aid,long section);
    List<IntegrationData> findByText(String text);
    List<IntegrationData> findBySection(long section);


}
