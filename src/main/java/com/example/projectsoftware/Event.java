package com.example.projectsoftware;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class Event {
    private int eventId;
    private String eventName;
    private Date eventDate;
    private String location;
    private String description;
    private int organizerId;
    private Timestamp creationDate;
    private byte[] image;
    private int hallId;
    private LocalDate date;


    // Constructor
    public Event(int eventId, String eventName, Date eventDate, String location, String description, int organizerId, Timestamp creationDate, byte[] image, int hallId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.description = description;
        this.organizerId = organizerId;
        this.creationDate = creationDate;
        this.image = image;
        this.hallId = hallId;
    }

    public Event(int id, String name, LocalDate date) {
        this.eventId = id;
        this.eventName = name;
        this.date = date;
    }

    @Override
    public String toString() {
        if (date != null) {
            return eventName + " - " + date.toString();
        } else {
            return eventName + " - No date available";
        }
    }

    public Event(int eventId, String eventName, Date eventDate, String location, String description, int organizerId, Timestamp creationDate, int hallId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.description = description;
        this.organizerId = organizerId;
        this.creationDate = creationDate;
        this.hallId = hallId;
    }

    // Getters
    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public byte[] getImage() {
        return image;
    }

    public int getHallId() {
        return hallId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

