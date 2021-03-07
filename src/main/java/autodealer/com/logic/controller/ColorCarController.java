package autodealer.com.logic.controller;

import autodealer.com.logic.dao.converter.Converter;
import autodealer.com.logic.dto.ColorCarDTO;
import autodealer.com.logic.service.ColorCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/colorCar")
public class ColorCarController {

    @Autowired
    ColorCarService colorCarService;

    @GetMapping("/read")
    public List<ColorCarDTO> readAllTypeCarBody() {
        return colorCarService.readAll()
                .stream()
                .map(Converter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/update")
    public ColorCarDTO update(@RequestBody ColorCarDTO colorCarDTO){
        return Converter.convertEntityToDto(colorCarService.update(Converter.convertDtoToEntity(colorCarDTO)));
    }

    @GetMapping("/create")
    public ColorCarDTO create(@RequestBody ColorCarDTO colorCarDTO){
        return Converter.convertEntityToDto(colorCarService.create(Converter.convertDtoToEntity(colorCarDTO)));
    }

    @GetMapping("/find/id/{id}")
    public ColorCarDTO findById(@PathVariable Long id){
        return Converter.convertEntityToDto(colorCarService.findById(id));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody ColorCarDTO colorCarDTO){
        colorCarService.delete(Converter.convertDtoToEntity(colorCarDTO));
    }
}
