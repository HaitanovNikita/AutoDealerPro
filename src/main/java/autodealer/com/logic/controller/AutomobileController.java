package autodealer.com.logic.controller;

import autodealer.com.logic.dto.AutomobileDTO;
import autodealer.com.logic.service.AutomobileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auto")
public class AutomobileController {

    private final AutomobileService automobileService;

    @Autowired
    public AutomobileController(AutomobileService automobileService) {
        this.automobileService = automobileService;
    }

    @ApiOperation(value = "Create new auto")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created new Auto"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<AutomobileDTO> save(@RequestBody AutomobileDTO automobileDTO) {
        return new ResponseEntity<>(automobileService.create(automobileDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete auto by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        automobileService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Delete auto")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody AutomobileDTO automobileDTO) {
        automobileService.deleteByID(automobileDTO.getID());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Read all auto")
    @GetMapping(value = "/readAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AutomobileDTO>> readAllAuto() {
        return new ResponseEntity<>(automobileService.readAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get popular auto")
    @GetMapping(value = "/mostPopularAuto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutomobileDTO> mostPopularAuto() {
        return new ResponseEntity<>(automobileService.getMostPopularAuto(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get profit to the gap")
    @GetMapping(value = "/profit/fromDate/{fromDate}/forDate/{forDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> profitForTheGap(@PathVariable("fromDate") String fromDate, @PathVariable("forDate") String forDate) {
        return new ResponseEntity<>(automobileService.getSalesProfitForTheGap(fromDate, forDate), HttpStatus.OK);
    }

    @ApiOperation(value = "Get auto by filter")
    @GetMapping(value = "/modelCar/{modelCar}/powerCar/{powerCar}/engineCar/{engineCar}/colorCar/{colorCar}/typeCarBody/{typeCarBody}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutomobileDTO> findByFilter(@PathVariable(name = "modelCar") Integer modelCar,
                                                      @PathVariable(name = "powerCar") Integer powerCar,
                                                      @PathVariable(name = "engineCar") Integer engineCar,
                                                      @PathVariable(name = "colorCar") Integer colorCar,
                                                      @PathVariable(name = "typeCarBody") Integer typeCarBody) {
        return new ResponseEntity<>(automobileService.findByAuto(modelCar, powerCar, engineCar, colorCar, typeCarBody), HttpStatus.OK);
    }

    @ApiOperation(value = "Find auto by id")
    @GetMapping("/find/id/{id}")
    public ResponseEntity<AutomobileDTO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(automobileService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Find auto by model car id")
    @PutMapping("/update")
    public ResponseEntity<AutomobileDTO> updateAuto(@RequestBody AutomobileDTO automobileDTO) {
        return new ResponseEntity<>(automobileService.updateAuto(automobileDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Find auto by model car id")
    @GetMapping("/find/modelCar/{modelCar}")
    public ResponseEntity<List<AutomobileDTO>> findByModelCarId(@PathVariable("modelCar") Long id) {
        return new ResponseEntity<>(automobileService.findByModelCar(id), HttpStatus.OK);
    }
}
