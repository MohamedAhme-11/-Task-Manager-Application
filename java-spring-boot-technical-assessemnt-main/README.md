📅 Task Manager Application
The Task Manager Application is a simple Spring Boot application designed to manage events with basic CRUD operations (Create, Read, Update, Delete).

Table of Contents
📦 Model
🗄️ Repository
🛠️ Service
🌐 Controller
🚀 Running the Application
🔗 API Endpoints
📦 Model
Event
The Event class represents an event entity with properties such as id, title, description, and date. It is annotated with @Entity to indicate that it is a JPA entity.

Fields
id: A unique identifier for the event (auto-generated).
title: The title of the event.
description: A brief description of the event.
date: The date and time when the event is scheduled.
Code
java
Copy code
package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Date date;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
🗄️ Repository
EventRepository
The EventRepository interface extends JpaRepository, providing CRUD operations for the Event entity.

Code
java
Copy code
package com.example.repository;

import com.example.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
🛠️ Service
EventService
The EventService class contains the business logic for managing events. It uses the EventRepository to interact with the database.

Methods
createEvent(Event event): Creates a new event.
getAllEvents(): Retrieves all events.
getEventById(Long id): Retrieves an event by its ID.
updateEvent(Long id, Event eventDetails): Updates an existing event.
deleteEvent(Long id): Deletes an event by its ID.
Code
java
Copy code
package com.example.service;

import com.example.model.Event;
import com.example.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Method to create a new event
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Method to retrieve all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Method to retrieve an event by its ID
    public Event getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null); // Return the event if found, otherwise return null
    }

    // Method to update an existing event
    public Event updateEvent(Long id, Event eventDetails) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.setTitle(eventDetails.getTitle());
            event.setDescription(eventDetails.getDescription());
            event.setDate(eventDetails.getDate());
            return eventRepository.save(event);
        } else {
            // Handle the case where the event does not exist
            return null;
        }
    }

    // Method to delete an event by its ID
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
🌐 Controller
EventController
The EventController class handles HTTP requests and responses. It uses the EventService to perform CRUD operations on events.

Endpoints
GET /events: Retrieves all events.
GET /events/{id}: Retrieves an event by its ID.
POST /events: Creates a new event.
PUT /events/{id}: Updates an existing event.
DELETE /events/{id}: Deletes an event by its ID.
Code
java
Copy code
package com.example.controller;

import com.example.model.Event;
import com.example.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        return eventService.updateEvent(id, eventDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
🚀 Running the Application
To run the Task Manager Application, follow these steps:

Clone the repository:

bash
Copy code
git clone <repository-url>
Navigate to the project directory:

bash
Copy code
cd task-manager
Build the project:

bash
Copy code
mvn clean install
Run the application:

bash
Copy code
mvn spring-boot:run
The application will start on http://localhost:8080.

🔗 API Endpoints
Create Event
URL: POST /events
Body: JSON representation of the Event object
Example Request:

json
Copy code
{
  "title": "Team Meeting",
  "description": "Discuss project updates",
  "date": "2024-05-25T10:00:00"
}
Get All Events
URL: GET /events
Get Event by ID
URL: GET /events/{id}
Update Event
URL: PUT /events/{id}
Body: JSON representation of the updated Event object
Delete Event
URL: DELETE /events/{id}
