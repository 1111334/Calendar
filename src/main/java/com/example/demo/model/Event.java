package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long event_id;
    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String location;

    public Event(String title, String date, String location) {
        this.title = title;
        this.date = new Date();
        this.location = location;
    }

    public Event() {

    }

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    @JsonIgnore
    private Calendar calendar;

    @PrePersist
    protected void onCreate() {date = new Date();}


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}

