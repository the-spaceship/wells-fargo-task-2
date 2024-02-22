package com.wellsfargo.counselor.controller;

import com.wellsfargo.counselor.payload.ClientDto;
import com.wellsfargo.counselor.repository.SecurityRepository;
import com.wellsfargo.counselor.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //http://localhost:8080/api/clients?advisorId=1
    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto, @RequestParam long advisorId){
       ClientDto dto = clientService.createClient(clientDto, advisorId);
       return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/clients/2
   @DeleteMapping("/{client_id}")
  public ResponseEntity<String> deleteClient(@PathVariable long client_id){
       clientService.deleteClient(client_id);
       return new ResponseEntity<>("client is deleted!", HttpStatus.OK);
}

    //http://localhost:8080/api/clients/4/advisor/2
@PutMapping("/{client_id}/advisor/{advisorId}")
public ResponseEntity<ClientDto> updateClient(@PathVariable long client_id, @RequestBody ClientDto clientDto, @PathVariable long advisorId){
       ClientDto dto = clientService.updateClient(client_id, clientDto, advisorId);
       return new ResponseEntity<>(dto,HttpStatus.OK);
}

}
