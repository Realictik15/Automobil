package com.automobil.backend.rest;

import com.automobil.backend.dto.*;
import com.automobil.backend.exeption.CLientException;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.ModelsMapper;
import com.automobil.backend.models.Clients;
import com.automobil.backend.models.Models;
import com.automobil.backend.service.*;
import com.automobil.backend.transfer.*;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientsController {
    private final ClientService clientService;

    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @JsonView(AdminDetails.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientsDto>> getAllClients() {
        List<ClientsDto> clientsDtos = this.clientService.listAll();
        if (clientsDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientsDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(Details.class)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientsDto> getClientByID(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clientService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addClient(@Validated(New.class) @RequestBody ClientsDto clientsDto) throws EntityNotFoundException {
        if (clientsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            clientService.save(clientsDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }


    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PatchMapping(value = "{id}/client", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchClient(@Validated(Existing.class) @PathVariable("id") Long id, @RequestBody ClientsDto clientsDto) throws DataIntegrityViolationException, CLientException, EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (clientsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        clientService.update(clientsDto);
        return new ResponseEntity(HttpStatus.CREATED);

    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "{id}/advert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdvertismentDto>> getClientAdvert(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clientService.getUserAdvert(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "{id}/compare", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComparisonsDto>> getClientCompareDto(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<ComparisonsDto> l= clientService.getUserCompareDto(id);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping (value = "/{id}/advertcomp/{idAd}", produces =
        MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addClientComp(@PathVariable("id") Long id, @PathVariable("idAd") Long idAd) throws EntityNotFoundException {
        if (id == null || idAd == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            clientService.saveUserCompare(id, idAd);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping(value = "{addmess}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMessage(@RequestBody MessagesDto messagesDto) throws EntityNotFoundException {
        if (messagesDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            clientService.addMessage(messagesDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @DeleteMapping(value = "comparedelete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCompare(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        clientService.deleteCompare(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
