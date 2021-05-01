package autodealer.com.logic.repository;

import autodealer.com.logic.entity.IntegrationData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author nhaitanov
 */
public interface IntegrationDataRepository extends CrudRepository<IntegrationData, Long>, Repository<IntegrationData, Long>{

    public List<IntegrationData> findAllBySection(long sectionId);
}
