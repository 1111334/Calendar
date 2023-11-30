package com.example.demo.controller;

import com.example.demo.DTO.InvitoDTO;
import com.example.demo.model.InvitationEvent;
import com.example.demo.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InviteEventController {

    @Autowired
    private InviteService inviteService;


    public InviteEventController(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    //@PostMapping("/invia-invito")
    //public void inviaInvito(@RequestBody InvitoDTO invitoDTO) {
    //    // Logica per gestire l'invio dell'invito e l'aggiunta all'evento
    //    inviteService.inviaInvito(invitoDTO.getEmail(), invitoDTO.getOggetto(), invitoDTO.getTesto());
    //}

    @PostMapping(value = "/insert-invite")
    public String insertInvite(@RequestBody InvitationEvent invitationEvent) {
        inviteService.inviaInvito(invitationEvent);
        return "new invite";
    }

    @GetMapping(value = "/api/invite/{InviteId}")
    public ResponseEntity<InvitoDTO> getInviteById(@PathVariable Long InviteId) {
        InvitationEvent invitationEvent = inviteService.getInviteById(InviteId);

        if (invitationEvent != null) {
            InvitoDTO invitoDTO = InvitoDTO.fromInvite(invitationEvent);
            return new ResponseEntity<>(invitoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(value = "/api/invite/{InviteId}")
    public ResponseEntity<HttpStatus> deleteInvite(@PathVariable Long InviteId) {
        inviteService.deleteInvite(InviteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
