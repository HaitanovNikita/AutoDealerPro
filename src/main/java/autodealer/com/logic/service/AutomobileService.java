package autodealer.com.logic.service;

import autodealer.com.logic.dto.AutomobileDTO;

public interface AutomobileService extends AutoDealerCRUDService<AutomobileDTO> {


    AutomobileDTO getMostPopularAuto();
    void deleteByID(long id);
    String getSalesProfitForTheGap(String fromDate,String forDate);
    AutomobileDTO findById(Long id);
    AutomobileDTO findByYear_issue_car(String yearIssueCar);
}
