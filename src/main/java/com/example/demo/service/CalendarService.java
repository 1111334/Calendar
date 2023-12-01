package com.example.demo.service;

import com.example.demo.model.Calendar;
import com.example.demo.model.Event;
import com.example.demo.model.User;
import com.example.demo.repository.CalendarRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;


    @Autowired
    UserRepository userRepository;


    public List<Calendar> viewCalendar() {
        return ResponseEntity.ok(calendarRepository.findAll()).getBody();
    }

    public void insertCalendar(Calendar calendar) {
        calendarRepository.save(calendar);
    }


    public Calendar createCalendar(Long userID, Calendar calendar) throws Exception{
        if (userRepository.findById(userID).isPresent()){
            User user = userRepository.findById(userID).orElse(null);

            calendar.setUser(user);

        } else {
            throw new Exception(String.format("User with ID %s not exist", userID));
        }

        return calendarRepository.save(calendar);
    }

    public void updateCalendar(Long calendarId, Calendar updatedCalendar) {
        Calendar calendar = calendarRepository.findById(calendarId).orElse(null);
        calendar.setName(updatedCalendar.getName());
        calendar.setDescription(updatedCalendar.getDescription());
        calendarRepository.save(calendar);
    }



    // EventService.java
    public void deleteCalendar(Long calendarId) {
        calendarRepository.deleteById(calendarId);
    }






}
