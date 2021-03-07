package autodealer.com.logic.controller;

import autodealer.com.logic.dao.converter.Converter;
import autodealer.com.logic.dto.ModelCarDTO;
import autodealer.com.logic.service.ModelCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/modelCar")
public class ModelCarController {

    @Autowired
    ModelCarService modelCarService;

    @GetMapping("/read")
    public List<ModelCarDTO> readAllTypeCarBody() {
        return modelCarService.readAll()
                .stream()
                .map(Converter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/update")
    public ModelCarDTO update(@RequestBody ModelCarDTO modelCarDTO){
        return Converter.convertEntityToDto(modelCarService.update(Converter.convertDtoToEntity(modelCarDTO)));
    }

    @GetMapping("/create")
    public ModelCarDTO create(@RequestBody ModelCarDTO modelCarDTO){
        return Converter.convertEntityToDto(modelCarService.create(Converter.convertDtoToEntity(modelCarDTO)));
    }

    @GetMapping("/find/id/{id}")
    public ModelCarDTO findById(@PathVariable Long id){
        return Converter.convertEntityToDto(modelCarService.findById(id));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody ModelCarDTO modelCarDTO){
        modelCarService.delete(Converter.convertDtoToEntity(modelCarDTO));
    }

}
