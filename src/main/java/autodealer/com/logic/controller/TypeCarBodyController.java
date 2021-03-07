package autodealer.com.logic.controller;

import autodealer.com.logic.dao.converter.Converter;
import autodealer.com.logic.dto.TypeCarBodyDTO;
import autodealer.com.logic.service.TypeCarBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/typeCarBody")
public class TypeCarBodyController {

    @Autowired
    TypeCarBodyService typeCarBodyService;

    @GetMapping("/read")
    public List<TypeCarBodyDTO> readAllTypeCarBody() {
        return typeCarBodyService.readAll()
                .stream()
                .map(Converter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/update")
    public TypeCarBodyDTO update(@RequestBody TypeCarBodyDTO typeCarBody){
        return Converter.convertEntityToDto(typeCarBodyService.update(Converter.convertDtoToEntity(typeCarBody)));
    }

    @GetMapping("/create")
    public TypeCarBodyDTO create(@RequestBody TypeCarBodyDTO typeCarBody){
        return Converter.convertEntityToDto(typeCarBodyService.create(Converter.convertDtoToEntity(typeCarBody)));
    }

    @GetMapping("/find/id/{id}")
    public TypeCarBodyDTO findById(@PathVariable Long id){
        return Converter.convertEntityToDto(typeCarBodyService.findById(id));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody TypeCarBodyDTO typeCarBodyDTO){
        typeCarBodyService.delete(Converter.convertDtoToEntity(typeCarBodyDTO));
    }

}

