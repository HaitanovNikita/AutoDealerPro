package autodealer.com.logic.service;

import autodealer.com.logic.dto.AutomobileDTO;

import java.util.List;

public interface AutomobileService extends AutoDealerCRUDService<AutomobileDTO> {

    AutomobileDTO findByAuto(Integer modelCar, Integer powerCar, Integer engineCar, Integer colorCar, Integer typeCarBody);
    List<AutomobileDTO> findByModelCar(Long id);
    AutomobileDTO getMostPopularAuto();
    void deleteByID(long id);
    String getSalesProfitForTheGap(String fromDate,String forDate);
    AutomobileDTO findById(Long id);

    AutomobileDTO updateAuto(AutomobileDTO automobileDTO);
}
