package com.dwwm.visiotech.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dwwm.visiotech.entities.Actor;
import com.dwwm.visiotech.repositories.ActorRepository;

@ExtendWith(MockitoExtension.class)
class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorService actorService;

    @Test
    void testSearchActors_returnsMatchingActors() {
        String searchTerm = "jean";
        List<Actor> found = new ArrayList<>();
        found.add(new Actor(1L, "Jean Dujardin"));
        found.add(new Actor(2L, "Jean Reno"));
        found.add(new Actor(3L, "Jean-Hugues Anglade"));
        when(actorRepository.findByNameContainingIgnoreCase(searchTerm)).thenReturn(found);

        List<Actor> result = actorService.searchActors(searchTerm);

        verify(actorRepository).findByNameContainingIgnoreCase(searchTerm);
        assertEquals(3, result.size());
        assertEquals("Jean Dujardin", result.get(0).getName());
        assertEquals("Jean Reno", result.get(1).getName());
        assertEquals("Jean-Hugues Anglade", result.get(2).getName());
    }

    @Test
    void testSearchActors_returnsEmptyList() {
        String searchTerm = "inconnu";
        when(actorRepository.findByNameContainingIgnoreCase(searchTerm)).thenReturn(new ArrayList<>());

        List<Actor> result = actorService.searchActors(searchTerm);

        verify(actorRepository).findByNameContainingIgnoreCase(searchTerm);
        assertTrue(result.isEmpty());
    }
}
