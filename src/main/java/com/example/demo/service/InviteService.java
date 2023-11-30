package com.example.demo.service;


import com.example.demo.model.InvitationEvent;
import com.example.demo.model.User;
import com.example.demo.repository.InviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class InviteService {

    @Autowired
    private InviteRepository inviteRepository;

    //private final JavaMailSender javaMailSender;

   // public InviteService(JavaMailSender javaMailSender) {
   //    this.javaMailSender = javaMailSender;
   //  }

    //public void inviaInvito(String email, String oggetto, String testo) {
    //    SimpleMailMessage message = new SimpleMailMessage();
    //    message.setTo(email);
    //    message.setSubject(oggetto);
    //    message.setText(testo);
    //    javaMailSender.send(message);
    //}

    public void inviaInvito(InvitationEvent invitationEvent) {
        inviteRepository.save(invitationEvent);
    }


    public InvitationEvent getInviteById(Long inviteId) {
        return inviteRepository.findById(inviteId).orElse(null);
    }

   public void deleteInvite(Long inviteId) {
       inviteRepository.deleteById(inviteId);
   }
}
