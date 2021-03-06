package autodealer.com.logic.service.impl;

import autodealer.com.logic.dao.converter.Converter;
import autodealer.com.logic.dao.impl.AutomobileDaoMySQl;
import autodealer.com.logic.dto.AutomobileDTO;
import autodealer.com.logic.service.AutomobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AutomobileServiceImpl implements AutomobileService {

    private final AutomobileDaoMySQl automobileDaoMySQl;

    @Autowired
    public AutomobileServiceImpl(AutomobileDaoMySQl automobileDaoMySQl) {
        this.automobileDaoMySQl = automobileDaoMySQl;
    }

    @Override
    public AutomobileDTO save(AutomobileDTO automobileDTO) {
        return Converter.convertEntityToDto(automobileDaoMySQl.save(Converter.convertDtoToEntity(automobileDTO)));
    }

    @Override
    public void delete(AutomobileDTO automobileDTO) {
        automobileDaoMySQl.deleteByID(automobileDTO.getID());
    }

    @Override
    public List<AutomobileDTO> readAll() {
        return automobileDaoMySQl
                .readAll()
                .stream()
                .map(Converter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AutomobileDTO getMostPopularAuto() {
        return Converter.convertEntityToDto(automobileDaoMySQl.getMostPopularAuto());
    }

    @Override
    public void deleteByID(long id) {
        automobileDaoMySQl.deleteByID(id);
    }

    @Override
    public String getSalesProfitForTheGap(String fromDate, String forDate) {
        return getSalesProfitForTheGap(fromDate, forDate);
    }

    @Override
    public AutomobileDTO findById(Long id) {
        return Converter.convertEntityToDto(automobileDaoMySQl.findById(id));
    }

//    @Override
//    public AutomobileDTO findByYear_issue_car(String yearIssueCar) {
//        return Converter.convertEntityToDto(automobileDaoMySQl.findByYear_issue_car(yearIssueCar));
//    }
}
