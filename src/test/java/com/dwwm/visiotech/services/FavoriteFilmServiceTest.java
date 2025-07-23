// package com.dwwm.visiotech.services;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.never;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.dwwm.visiotech.entities.FavoriteFilm;
// import com.dwwm.visiotech.entities.FavoriteFilm.FavoriteFilmId;
// import com.dwwm.visiotech.repositories.FavoriteFilmRepository;

// @ExtendWith(MockitoExtension.class)
// class FavoriteFilmServiceTest {

//     @Mock
//     private FavoriteFilmRepository favoriteFilmRepository;

//     @InjectMocks
//     private FavoriteFilmService favoriteFilmService;

//     @Test
//     void testAddFavorite_savesAndReturnsFavoriteFilm() {
//         Long userId = 42L;
//         Long filmId = 7L;
//         FavoriteFilmId id = new FavoriteFilmId(userId, filmId);
//         FavoriteFilm saved = new FavoriteFilm(userId, filmId);
//         when(favoriteFilmRepository.existsById(id)).thenReturn(false);
//         when(favoriteFilmRepository.save(any(FavoriteFilm.class))).thenReturn(saved);

//         FavoriteFilm result = favoriteFilmService.addFavorite(userId, filmId);

//         verify(favoriteFilmRepository).existsById(id);
//         verify(favoriteFilmRepository).save(any(FavoriteFilm.class));
//         assertEquals(userId, result.getUserId());
//         assertEquals(filmId, result.getFilmId());
//     }

//     @Test
//     void testAddFavorite_throwsException_whenFavoriteAlreadyAdded() {
//         Long userId = 99L;
//         Long filmId = 12L;
//         FavoriteFilmId id = new FavoriteFilmId(userId, filmId);
//         when(favoriteFilmRepository.existsById(id)).thenReturn(true);
//         IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                 () -> favoriteFilmService.addFavorite(userId, filmId));

//         assertEquals("Film déjà ajouté aux favoris", exception.getMessage());
//         verify(favoriteFilmRepository).existsById(id);
//         verify(favoriteFilmRepository, never()).save(any());
//     }

//     @Test
//     void testDeleteFavorite_successfullyDeletesFavorite() {
//         Long userId = 42L;
//         Long filmId = 7L;
//         FavoriteFilmId id = new FavoriteFilmId(userId, filmId);
//         when(favoriteFilmRepository.existsById(id)).thenReturn(true);

//         favoriteFilmService.deleteFavorite(userId, filmId);

//         verify(favoriteFilmRepository).existsById(id);
//         verify(favoriteFilmRepository).deleteById(id);
//     }

//     @Test
//     void testDeleteFavorite_throwsException_whenFavoriteNotFound() {
//         Long userId = 99L;
//         Long filmId = 12L;
//         FavoriteFilmId id = new FavoriteFilmId(userId, filmId);
//         when(favoriteFilmRepository.existsById(id)).thenReturn(false);

//         IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                 () -> favoriteFilmService.deleteFavorite(userId, filmId));

//         assertEquals("Favori non trouvé", exception.getMessage());
//         verify(favoriteFilmRepository).existsById(id);
//         verify(favoriteFilmRepository, never()).deleteById(any());
//     }
// }
