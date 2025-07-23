package com.dwwm.visiotech.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dwwm.visiotech.entities.Actor;
import com.dwwm.visiotech.services.ActorService;

@RestController
public class ActorController {

    private final ActorService actorService ;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }
    
    @GetMapping("/actors/search")
    public ResponseEntity<List<Actor>> searchActors(@RequestParam String name){
        List<Actor> actors = actorService.searchActors(name);
        return ResponseEntity.ok(actors);
    }
}
