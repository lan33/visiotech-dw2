package com.dwwm.visiotech.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dwwm.visiotech.entities.Actor;
import com.dwwm.visiotech.repositories.ActorRepository;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> searchActors(String name) {
        return actorRepository.findByNameContainingIgnoreCase(name);
    }

    public List<String> searchActors2(String name) {
        List<Actor> actors = actorRepository.findByNameContainingIgnoreCase(name);
        List<String> names = new ArrayList<>();
        for (Actor actor : actors) {
            names.add(actor.getName());
        }
        return names;
    }

    public List<String> searchActors3(String name) {
        List<Actor> actors = actorRepository.findByNameContainingIgnoreCase(name);
        List<String> names = new ArrayList<>();
        actors.forEach(actor -> names.add(actor.getName()));
        return names;
    }

    public List<String> searchActors4(String name) {
        List<Actor> actors = actorRepository.findByNameContainingIgnoreCase(name);
        return actors.stream().map(Actor::getName).toList();
    }
}
