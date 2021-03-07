package autodealer.com.logic.controller;

import autodealer.com.logic.dto.ClientDTO;
import autodealer.com.logic.service.ClientService;
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
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Read all clients")
    @GetMapping(value = "/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientDTO>> readAllClients() {
        return new ResponseEntity<>(clientService.readAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create new client")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created new client"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO client) {
        return new ResponseEntity<>(clientService.create(client), HttpStatus.OK);
    }

    @ApiOperation(value = "Update client")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated client"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO client) {
        return new ResponseEntity<>(clientService.create(client), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete client")
    @DeleteMapping("/delete")
    public ResponseEntity<Long> deleteClient(@RequestBody ClientDTO client) {
        clientService.delete(client);
        return new ResponseEntity<>((long) client.getID(), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete client")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable Integer id) {
        clientService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @ApiOperation(value = "Find client by id")
    @GetMapping("/find/id/{id}")
    public ResponseEntity<ClientDTO> findClient(@PathVariable Integer id) {
        return new ResponseEntity<>(clientService.findClient(id), HttpStatus.OK);
    }


    @ApiOperation(value = "Find client by phone number")
    @GetMapping("/find/phone/{phone}")
    public ResponseEntity<ClientDTO> findByClient_phone_num(@PathVariable String phone) {
        return new ResponseEntity<>(clientService.findByClient_phone_num(phone), HttpStatus.OK);
    }
}
