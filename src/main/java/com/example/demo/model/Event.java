package com.example.demo.model;


import com.example.demo.Util.RecurrenceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long event_id;
    private String title;
    private String location;

    //@Temporal(TemporalType.TIMESTAMP)
    //private Date date;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime toStart;

    //@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime theEnd;

    private RecurrenceType recurrenceType;

    @JsonIgnore
    private Integer recurrenceInterval;

    public Event() {

    }

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    @JsonIgnore
    private Calendar calendar;

    //@PrePersist
    //protected void onCreate() {date = new Date();}

    @ManyToMany(mappedBy = "events")
    @JsonIgnore
    private List<User> users;


    public Long getEventId() {
        return event_id;
    }

    public void setEventId(Long eventId) {
        this.event_id = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getToStart() {
        return toStart;
    }

    public void setToStart(LocalDateTime toStart) {
        this.toStart = toStart;
    }

    public LocalDateTime getTheEnd() {
        return theEnd;
    }

    public void setTheEnd(LocalDateTime theEnd) {
        this.theEnd = theEnd;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public RecurrenceType getRecurrenceType() {
        return recurrenceType;
    }

    public void setRecurrenceType(RecurrenceType recurrenceType) {
        this.recurrenceType = recurrenceType;
    }

    public Integer getRecurrenceInterval() {
        return recurrenceInterval;
    }

    public void setRecurrenceInterval(Integer recurrenceInterval) {
        this.recurrenceInterval = recurrenceInterval;
    }
}

