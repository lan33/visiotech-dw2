// // package com.dwwm.visiotech.services;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.never;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import com.dwwm.visiotech.entities.Category;
// import com.dwwm.visiotech.entities.FilmCategory;
// import com.dwwm.visiotech.entities.FilmCategory.FilmCategoryId;
// import com.dwwm.visiotech.entities.WatchedFilm.WatchedFilmId;
// import com.dwwm.visiotech.repositories.CategoryRepository;
// import com.dwwm.visiotech.repositories.FilmCategoryRepository;
// import com.dwwm.visiotech.repositories.WatchedFilmRepository;

// public class FilmCategoryServiceTest {

//     @Mock
//     private FilmCategoryRepository filmCategoryRepository;

//     @Mock
//     private CategoryRepository categoryRepository;

//     @Mock
//     private WatchedFilmRepository watchedFilmRepository;

//     @InjectMocks
//     private FilmCategoryService filmCategoryService;

//     @BeforeEach
//     void setup() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     void testAssociateCategoryToWatchedFilm_savesAndReturnsFilmCategory() {
//         Long userId = 1L;
//         Long filmId = 100L;
//         Long categoryId = 10L;
//         WatchedFilmId watchedFilmId = new WatchedFilmId(userId, filmId);
//         Category mockCategory = new Category("Action", userId);
//         FilmCategoryId filmCategoryId = new FilmCategoryId(filmId, categoryId);
//         FilmCategory saved = new FilmCategory(filmId, categoryId);
//         when(watchedFilmRepository.existsById(watchedFilmId)).thenReturn(true);
//         when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));
//         when(filmCategoryRepository.existsById(filmCategoryId)).thenReturn(false);
//         when(filmCategoryRepository.save(any())).thenReturn(saved);

//         FilmCategory result = filmCategoryService.associateCategoryToWatchedFilm(userId, filmId, categoryId);

//         verify(watchedFilmRepository).existsById(watchedFilmId);
//         verify(categoryRepository).findById(categoryId);
//         verify(filmCategoryRepository).existsById(filmCategoryId);
//         verify(filmCategoryRepository).save(any(FilmCategory.class));
//         assertEquals(filmId, result.getFilmId());
//         assertEquals(categoryId, result.getCategoryId());
//     }

//     @Test
//     void testAssociateCategoryToWatchedFilm_throwsException_whenFilmNotWatched() {
//         Long userId = 1L;
//         Long filmId = 100L;
//         Long categoryId = 10L;
//         WatchedFilmId watchedFilmId = new WatchedFilmId(userId, filmId);
//         when(watchedFilmRepository.existsById(watchedFilmId)).thenReturn(false);

//         Exception ex = assertThrows(IllegalArgumentException.class,
//                 () -> filmCategoryService.associateCategoryToWatchedFilm(userId, filmId, categoryId));

//         assertEquals("Ce film n’a pas été marqué comme visionné", ex.getMessage());
//         verify(watchedFilmRepository).existsById(watchedFilmId);
//         verify(categoryRepository, never()).findById(any());
//         verify(filmCategoryRepository, never()).existsById(any());
//         verify(filmCategoryRepository, never()).save(any());
//     }

//     @Test
//     void testAssociateCategoryToWatchedFilm_throwsException_whenCategoryNotFound() {
//         Long userId = 1L;
//         Long filmId = 100L;
//         Long categoryId = 10L;
//         WatchedFilmId watchedFilmId = new WatchedFilmId(userId, filmId);
//         when(watchedFilmRepository.existsById(watchedFilmId)).thenReturn(true);
//         when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

//         Exception ex = assertThrows(IllegalArgumentException.class,
//                 () -> filmCategoryService.associateCategoryToWatchedFilm(userId, filmId, categoryId));

//         assertEquals("Catégorie non trouvée", ex.getMessage());
//         verify(watchedFilmRepository).existsById(watchedFilmId);
//         verify(categoryRepository).findById(categoryId);
//         verify(filmCategoryRepository, never()).existsById(any());
//         verify(filmCategoryRepository, never()).save(any());
//     }

//     @Test
//     void testAssociateCategoryToWatchedFilm_throwsException_whenCategoryDoesNotBelongToUser() {
//         Long userId = 1L;
//         Long filmId = 100L;
//         Long categoryId = 10L;
//         Category mockCategory = new Category("Action", 2L);
//         WatchedFilmId watchedFilmId = new WatchedFilmId(userId, filmId);
//         when(watchedFilmRepository.existsById(watchedFilmId)).thenReturn(true);
//         when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));

//         Exception ex = assertThrows(IllegalArgumentException.class,
//                 () -> filmCategoryService.associateCategoryToWatchedFilm(userId, filmId, categoryId));

//         assertEquals("Cette catégorie n'appartient pas à l'utilisateur", ex.getMessage());
//         verify(watchedFilmRepository).existsById(watchedFilmId);
//         verify(categoryRepository).findById(categoryId);
//         verify(filmCategoryRepository, never()).existsById(any());
//         verify(filmCategoryRepository, never()).save(any());
//     }

//     @Test
//     void testAssociateCategoryToWatchedFilm_throwsException_whenAssociationAlreadyExists() {
//         Long userId = 1L;
//         Long filmId = 100L;
//         Long categoryId = 10L;
//         WatchedFilmId watchedFilmId = new WatchedFilmId(userId, filmId);
//         Category mockCategory = new Category("Action", userId);
//         FilmCategoryId filmCategoryId = new FilmCategoryId(filmId, categoryId);
//         when(watchedFilmRepository.existsById(watchedFilmId)).thenReturn(true);
//         when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));
//         when(filmCategoryRepository.existsById(filmCategoryId)).thenReturn(true);

//         Exception ex = assertThrows(IllegalStateException.class,
//                 () -> filmCategoryService.associateCategoryToWatchedFilm(userId, filmId, categoryId));

//         assertEquals("Cette catégorie est déjà associée au film", ex.getMessage());
//         verify(watchedFilmRepository).existsById(watchedFilmId);
//         verify(categoryRepository).findById(categoryId);
//         verify(filmCategoryRepository).existsById(filmCategoryId);
//         verify(filmCategoryRepository, never()).save(any());
//     }
// }
