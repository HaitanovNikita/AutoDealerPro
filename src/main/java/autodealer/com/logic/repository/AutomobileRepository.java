package autodealer.com.logic.repository;

import autodealer.com.logic.entity.Automobile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface AutomobileRepository extends CrudRepository<Automobile, Long>, Repository<Automobile, Long> {

    Iterable<Automobile> findAllByYear_issue_car(String yearIssueCar);

    String getSalesProfitForTheGap(String fromDate,String forDate);

    Optional<Automobile> findById(Long id);

    Optional<Automobile> findByYear_issue_car(String yearIssueCar);

    void deleteByID(long id);
}
