// package com.dwwm.visiotech.services;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.ArrayList;
// import java.util.List;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.dwwm.visiotech.entities.Film;
// import com.dwwm.visiotech.repositories.FilmRepository;

// @ExtendWith(MockitoExtension.class)
// class FilmServiceTest {

//     @Mock
//     private FilmRepository filmRepository;

//     @InjectMocks
//     private FilmService filmService;

//     @Test
//     void testGetAllFilms_returnsAllFilms() {
//         List<Film> mockFilms = new ArrayList<>();
//         mockFilms.add(new Film(5L, "Un ours dans le Jura"));
//         mockFilms.add(new Film(10L, "Kaamelott: The Second Chapter"));
//         when(filmRepository.findAll()).thenReturn(mockFilms);

//         List<Film> result = filmService.getAllFilms();

//         verify(filmRepository).findAll();
//         assertEquals(2, result.size());
//         assertEquals("Un ours dans le Jura", result.get(0).getTitle());
//         assertEquals("Kaamelott: The Second Chapter", result.get(1).getTitle());
//     }

//     @Test
//     void testGetAllFilms_returnsEmptyList() {
//         when(filmRepository.findAll()).thenReturn(new ArrayList<>());

//         List<Film> result = filmService.getAllFilms();

//         verify(filmRepository).findAll();
//         assertTrue(result.isEmpty());
//     }

//     @Test
//     void testSearchFilms_returnsMatchingFilms() {
//         String searchTerm = "Kaamelott";
//         List<Film> found = new ArrayList<>();
//         found.add(new Film(10L, "Kaamelott: The Second Chapter"));
//         when(filmRepository.findByTitleContainingIgnoreCase(searchTerm)).thenReturn(found);

//         List<Film> result = filmService.searchFilms(searchTerm);

//         verify(filmRepository).findByTitleContainingIgnoreCase(searchTerm);
//         assertEquals(1, result.size());
//         assertEquals("Kaamelott: The Second Chapter", result.get(0).getTitle());
//     }

//     @Test
//     void testSearchFilms_returnsEmptyList() {
//         String query = "FilmInexistant";
//         when(filmRepository.findByTitleContainingIgnoreCase(query)).thenReturn(new ArrayList<>());

//         List<Film> result = filmService.searchFilms(query);

//         verify(filmRepository).findByTitleContainingIgnoreCase(query);
//         assertTrue(result.isEmpty());
//     }
// }
