package autodealer.com.logic.controller;

import autodealer.com.logic.dao.converter.Converter;
import autodealer.com.logic.dto.EngineCarDTO;
import autodealer.com.logic.service.EngineCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/engineCar")
public class EngineCarController {

    @Autowired
    EngineCarService engineCarService;

    @GetMapping("/read")
    public List<EngineCarDTO> readAllTypeCarBody() {
        return engineCarService.readAll()
                .stream()
                .map(Converter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/update")
    public EngineCarDTO update(@RequestBody EngineCarDTO engineCarDTO){
        return Converter.convertEntityToDto(engineCarService.update(Converter.convertDtoToEntity(engineCarDTO)));
    }

    @GetMapping("/create")
    public EngineCarDTO create(@RequestBody EngineCarDTO engineCarDTO){
        return Converter.convertEntityToDto(engineCarService.create(Converter.convertDtoToEntity(engineCarDTO)));
    }

    @GetMapping("/find/id/{id}")
    public EngineCarDTO findById(@PathVariable Long id){
        return Converter.convertEntityToDto(engineCarService.findById(id));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody EngineCarDTO engineCarDTO){
        engineCarService.delete(Converter.convertDtoToEntity(engineCarDTO));
    }
}
