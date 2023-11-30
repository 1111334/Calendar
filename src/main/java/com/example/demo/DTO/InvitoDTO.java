package com.example.demo.DTO;

import com.example.demo.model.InvitationEvent;

public class InvitoDTO {

    private String email;
    private String oggetto;
    private String testo;

    public InvitoDTO() {

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public static InvitoDTO fromInvite(InvitationEvent invitationEvent) {
        InvitoDTO invitoDTO = new InvitoDTO();
        invitoDTO.setEmail(invitationEvent.getEmail());
        invitoDTO.setOggetto(invitationEvent.getOggetto());
        invitoDTO.setTesto(invitationEvent.getTesto());
        return invitoDTO;
    }
}
