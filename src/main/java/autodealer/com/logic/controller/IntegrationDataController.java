package autodealer.com.logic.controller;

import autodealer.com.logic.dto.IntegrationDataDTO;
import autodealer.com.logic.service.IntegrationDataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nhaitanov
 */
@RestController
@RequestMapping("/data")
public class IntegrationDataController {

    @Autowired
    private IntegrationDataService integrationDataService;

    @ApiOperation(value = "Read all integration data")
    @GetMapping(value = "/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IntegrationDataDTO>> readAll() {
        return new ResponseEntity<>(integrationDataService.readAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Find integration data by id")
    @PostMapping(value = "/find/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IntegrationDataDTO> findById(@PathVariable long id) {
        return new ResponseEntity<>(integrationDataService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create integration data")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created new integration text"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<IntegrationDataDTO> create(@RequestBody IntegrationDataDTO integrationDataDTO) {
        return new ResponseEntity<>(integrationDataService.save(integrationDataDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete integration data")
    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(@RequestBody IntegrationDataDTO integrationDataDTO) {
        integrationDataService.delete(integrationDataDTO);
        return new ResponseEntity<>(integrationDataDTO.getId(), HttpStatus.OK);
    }

    @ApiOperation(value = "Find data by answer id and section")
    @GetMapping(value = "/find/aid/{aid}/section/{section}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IntegrationDataDTO>> findByAnswerIdAndSection(@PathVariable long aid, @PathVariable long section) {
        return new ResponseEntity<>(integrationDataService.findByAnswerIdAndSection(aid, section), HttpStatus.OK);
    }

    @ApiOperation(value = "Find data by text")
    @GetMapping(value = "/find/text/{text}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IntegrationDataDTO>> findByText(@PathVariable String text) {
        return new ResponseEntity<>(integrationDataService.findByText(text), HttpStatus.OK);
    }

    @ApiOperation(value = "Find data by section")
    @GetMapping(value = "/find/section/{section}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IntegrationDataDTO>> findBySection(@PathVariable long section) {
        return new ResponseEntity<>(integrationDataService.findBySection(section), HttpStatus.OK);
    }
}
