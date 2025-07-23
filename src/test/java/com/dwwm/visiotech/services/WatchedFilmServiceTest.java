package com.dwwm.visiotech.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dwwm.visiotech.entities.WatchedFilm;
import com.dwwm.visiotech.entities.WatchedFilm.WatchedFilmId;
import com.dwwm.visiotech.repositories.FilmRepository;
import com.dwwm.visiotech.repositories.UserRepository;
import com.dwwm.visiotech.repositories.WatchedFilmRepository;

@ExtendWith(MockitoExtension.class)
class WatchedFilmServiceTest {

    @Mock
    private WatchedFilmRepository watchedFilmRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private WatchedFilmService watchedFilmService;

    @Test
    void testMarkAsWatched_savesAndReturnsWatchedFilm() {
        // Arrange
        Long userId = 42L;
        Long filmId = 7L;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        WatchedFilm saved = new WatchedFilm(userId, filmId);
        when(watchedFilmRepository.existsById(id)).thenReturn(false);
        when(watchedFilmRepository.save(any(WatchedFilm.class))).thenReturn(saved);

        // Act
        WatchedFilm result = watchedFilmService.addWatched(userId, filmId);

        // Assert
        verify(watchedFilmRepository).existsById(id);
        verify(watchedFilmRepository).save(any(WatchedFilm.class));
        assertEquals(userId, result.getUserId());
        assertEquals(filmId, result.getFilmId());
    }

    @Test
    void testMarkAsWatched_throwsException_whenFilmAlreadyMarkedAsWatched() {
        Long userId = 1L;
        Long filmId = 10L;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        Mockito.when(watchedFilmRepository.existsById(id)).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> watchedFilmService.addWatched(userId, filmId));

        assertEquals("Ce film a déjà été marqué comme visionné", exception.getMessage());
        verify(watchedFilmRepository).existsById(id);
        verify(watchedFilmRepository, never()).save(any());
    }

    @Test
    void testUnmarkAsWatched_successfullyDeletesWatchedFilm() {
        Long userId = 42L;
        Long filmId = 7L;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        when(watchedFilmRepository.existsById(id)).thenReturn(true);

        watchedFilmService.deleteWatched(userId, filmId);

        verify(watchedFilmRepository).existsById(id);
        verify(watchedFilmRepository).deleteById(id);
    }

    @Test
    void testUnmarkAsWatched_throwsException_whenFilmNotWatched() {
        Long userId = 99L;
        Long filmId = 21L;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        when(watchedFilmRepository.existsById(id)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> watchedFilmService.deleteWatched(userId, filmId));

        assertEquals("Ce film n’a pas été marqué comme visionné", exception.getMessage());
        verify(watchedFilmRepository).existsById(id);
        verify(watchedFilmRepository, never()).deleteById(any());
    }

    @Test
    void testRateWatchedFilm_savesRatingAndReturnsWatchedFilm() {
        Long userId = 42L;
        Long filmId = 7L;
        int rating = 10;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        WatchedFilm existing = new WatchedFilm(userId, filmId);
        when(watchedFilmRepository.findById(id)).thenReturn(Optional.of(existing));
        when(watchedFilmRepository.save(existing)).thenReturn(existing);

        WatchedFilm result = watchedFilmService.rateWatchedFilm(userId, filmId, rating);

        verify(watchedFilmRepository).findById(id);
        verify(watchedFilmRepository).save(existing);
        assertEquals(rating, result.getRating());
        assertEquals(userId, result.getUserId());
        assertEquals(filmId, result.getFilmId());
    }

    @Test
    void testRateWatchedFilm_throwsException_whenNotMarkedAsWatched() {
        Long userId = 99L;
        Long filmId = 21L;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        when(watchedFilmRepository.findById(id)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> watchedFilmService.rateWatchedFilm(userId, filmId, 5));

        assertEquals("Ce film n’a pas été marqué comme visionné", exception.getMessage());
        verify(watchedFilmRepository).findById(id);
        verify(watchedFilmRepository, never()).save(any());
    }

    @Test
    void testUpdateRating_updatesRatingAndReturnsWatchedFilm() {
        Long userId = 42L;
        Long filmId = 7L;
        int newRating = 5;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        WatchedFilm existing = new WatchedFilm(userId, filmId);
        existing.setRating(10);
        when(watchedFilmRepository.findById(id)).thenReturn(Optional.of(existing));
        when(watchedFilmRepository.save(existing)).thenReturn(existing);

        WatchedFilm result = watchedFilmService.updateRating(userId, filmId, newRating);

        verify(watchedFilmRepository).findById(id);
        verify(watchedFilmRepository).save(existing);
        assertEquals(newRating, result.getRating());
        assertEquals(userId, result.getUserId());
        assertEquals(filmId, result.getFilmId());
    }

    @Test
    void testUpdateRating_throwsException_whenFilmNotWatched() {
        Long userId = 99L;
        Long filmId = 21L;
        int newRating = 5;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        when(watchedFilmRepository.findById(id)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> watchedFilmService.updateRating(userId, filmId, newRating));

        assertEquals("Ce film n’a pas été marqué comme visionné", exception.getMessage());
        verify(watchedFilmRepository).findById(id);
        verify(watchedFilmRepository, never()).save(any());
    }

    @Test
    void testDeleteRating_setsRatingToNullAndReturnsWatchedFilm() {
        Long userId = 42L;
        Long filmId = 7L;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        WatchedFilm existing = new WatchedFilm(userId, filmId);
        existing.setRating(5);
        when(watchedFilmRepository.findById(id)).thenReturn(Optional.of(existing));
        when(watchedFilmRepository.save(existing)).thenReturn(existing);

        WatchedFilm result = watchedFilmService.deleteRating(userId, filmId);

        verify(watchedFilmRepository).findById(id);
        verify(watchedFilmRepository).save(existing);
        assertNull(result.getRating());
        assertEquals(userId, result.getUserId());
        assertEquals(filmId, result.getFilmId());
    }

    @Test
    void testDeleteRating_throwsException_whenFilmNotWatched() {
        Long userId = 99L;
        Long filmId = 21L;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        when(watchedFilmRepository.findById(id)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> watchedFilmService.deleteRating(userId, filmId));

        assertEquals("Ce film n’a pas été marqué comme visionné", ex.getMessage());
        verify(watchedFilmRepository).findById(id);
        verify(watchedFilmRepository, never()).save(any());
    }

    @Test
    void testCommentOnWatchedFilm_setsCommentAndReturnsWatchedFilm() {
        Long userId = 42L;
        Long filmId = 7L;
        String comment = "Super film !";
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        WatchedFilm existing = new WatchedFilm(userId, filmId);
        when(watchedFilmRepository.findById(id)).thenReturn(Optional.of(existing));
        when(watchedFilmRepository.save(existing)).thenReturn(existing);

        WatchedFilm result = watchedFilmService.commentOnWatchedFilm(userId, filmId, comment);

        verify(watchedFilmRepository).findById(id);
        verify(watchedFilmRepository).save(existing);
        assertEquals(comment, result.getComment());
    }

    @Test
    void testCommentOnWatchedFilm_throwsException_whenFilmNotWatched() {
        Long userId = 404L;
        Long filmId = 10L;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        when(watchedFilmRepository.findById(id)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> watchedFilmService.commentOnWatchedFilm(userId, filmId, "Inexistant"));

        assertEquals("Ce film n’a pas été marqué comme visionné", ex.getMessage());
        verify(watchedFilmRepository).findById(id);
        verify(watchedFilmRepository, never()).save(any());
    }

    @Test
    void testUpdateComment_setsNewCommentAndReturnsWatchedFilm() {
        Long userId = 42L;
        Long filmId = 7L;
        String newComment = "J’ai changé d’avis, bof...";
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        WatchedFilm existing = new WatchedFilm(userId, filmId);
        existing.setComment("Super film !");
        when(watchedFilmRepository.findById(id)).thenReturn(Optional.of(existing));
        when(watchedFilmRepository.save(existing)).thenReturn(existing);

        WatchedFilm result = watchedFilmService.updateComment(userId, filmId, newComment);

        verify(watchedFilmRepository).findById(id);
        verify(watchedFilmRepository).save(existing);
        assertEquals(newComment, result.getComment());
    }

    @Test
    void testUpdateComment_throwsException_whenFilmNotWatched() {
        Long userId = 404L;
        Long filmId = 12L;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        when(watchedFilmRepository.findById(id)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> watchedFilmService.updateComment(userId, filmId, "Inexistant"));

        assertEquals("Ce film n’a pas été marqué comme visionné", ex.getMessage());
        verify(watchedFilmRepository).findById(id);
        verify(watchedFilmRepository, never()).save(any());
    }

    @Test
    void testDeleteComment_setsCommentNullAndReturnsWatchedFilm() {
        Long userId = 99L;
        Long filmId = 21L;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        WatchedFilm existing = new WatchedFilm(userId, filmId);
        existing.setComment("Commentaire initial");
        when(watchedFilmRepository.findById(id)).thenReturn(Optional.of(existing));
        when(watchedFilmRepository.save(existing)).thenReturn(existing);

        WatchedFilm result = watchedFilmService.deleteComment(userId, filmId);

        verify(watchedFilmRepository).findById(id);
        verify(watchedFilmRepository).save(existing);
        assertNull(result.getComment());
    }

    @Test
    void testDeleteComment_throwsException_whenFilmNotWatched() {
        Long userId = 404L;
        Long filmId = 15L;
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        when(watchedFilmRepository.findById(id)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> watchedFilmService.deleteComment(userId, filmId));

        assertEquals("Ce film n’a pas été marqué comme visionné", ex.getMessage());
        verify(watchedFilmRepository).findById(id);
        verify(watchedFilmRepository, never()).save(any());
    }
}
