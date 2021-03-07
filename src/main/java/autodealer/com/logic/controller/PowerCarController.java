package autodealer.com.logic.controller;

import autodealer.com.logic.dao.converter.Converter;
import autodealer.com.logic.dto.PowerCarDTO;
import autodealer.com.logic.service.PowerCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/powerCar")
public class PowerCarController {

    @Autowired
    PowerCarService powerCarService;

    @GetMapping("/read")
    public List<PowerCarDTO> readAllTypeCarBody() {
        return powerCarService.readAll()
                .stream()
                .map(Converter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/update")
    public PowerCarDTO update(@RequestBody PowerCarDTO powerCarDTO){
        return Converter.convertEntityToDto(powerCarService.update(Converter.convertDtoToEntity(powerCarDTO)));
    }

    @GetMapping("/create")
    public PowerCarDTO create(@RequestBody PowerCarDTO powerCarDTO){
        return Converter.convertEntityToDto(powerCarService.create(Converter.convertDtoToEntity(powerCarDTO)));
    }

    @GetMapping("/find/id/{id}")
    public PowerCarDTO findById(@PathVariable Long id){
        return Converter.convertEntityToDto(powerCarService.findById(id));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody PowerCarDTO powerCarDTO){
        powerCarService.delete(Converter.convertDtoToEntity(powerCarDTO));
    }


}
