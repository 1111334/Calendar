package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class InvitationEvent {

    @Id
    @GeneratedValue
    private Long InviteId;
    private String email;
    private String oggetto;
    private String testo;

    public InvitationEvent() {

    }

    public Long getInviteId() {
        return InviteId;
    }

    public void setInviteId(Long inviteId) {
        InviteId = inviteId;
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
}
