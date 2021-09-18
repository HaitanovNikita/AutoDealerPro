package autodealer.com.logic.repository;

import autodealer.com.logic.entity.Manager;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ManagerRepository extends PagingAndSortingRepository<Manager, Long> {

    List<Manager> findAll();

    Manager findByLoginAndPassword(String login, String password);

    Manager findByEmail(String email);

    Manager findByPhone(String phone);

}
