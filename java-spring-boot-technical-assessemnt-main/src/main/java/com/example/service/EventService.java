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
