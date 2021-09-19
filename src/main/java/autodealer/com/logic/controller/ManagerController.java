package autodealer.com.logic.controller;

import autodealer.com.logic.dto.ManagerDTO;
import autodealer.com.logic.service.ManagerService;
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
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping(value = "/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ManagerDTO>> readAllManager() {
        return new ResponseEntity<>(managerService.readAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create new manager")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created new manager"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<ManagerDTO> save(@RequestBody ManagerDTO managerDTO) {
        return new ResponseEntity<>(managerService.save(managerDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete manager")
    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(@RequestBody ManagerDTO manager) {
        managerService.delete(manager);
        return new ResponseEntity<>((long) manager.getId(), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete manager")
    @GetMapping("/find/id/{id}")
    public ResponseEntity<ManagerDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(managerService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Find manager")
    @PostMapping("/find/login/{login}/password/{password}")
    public ResponseEntity<ManagerDTO> findByLoginAndPassword(@PathVariable String login, @PathVariable String password) {
        return new ResponseEntity<>(managerService.findByLoginAndPassword(login, password), HttpStatus.OK);
    }


}
