package autodealer.com.logic.service;

import autodealer.com.logic.entity.TypeCarBody;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface TypeCarBodyService extends AutoDealerCRUDService<TypeCarBody> {

    TypeCarBody update(TypeCarBody object);

    TypeCarBody findById(Long id);
}
