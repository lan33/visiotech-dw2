// package com.dwwm.visiotech.services;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.never;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.dwwm.visiotech.entities.Category;
// import com.dwwm.visiotech.repositories.CategoryRepository;

// @ExtendWith(MockitoExtension.class)
// class CategoryServiceTest {

//     @Mock
//     private CategoryRepository categoryRepository;

//     @InjectMocks
//     private CategoryService categoryService;

//     @Test
//     void testAddCategory_savesAndReturnsCategory() {
//         String name = "Comédie";
//         Long userId = 1L;
//         Category saved = new Category(name, userId);
//         when(categoryRepository.existsByNameAndUserId(name, userId)).thenReturn(false);
//         when(categoryRepository.save(any(Category.class))).thenReturn(saved);

//         Category result = categoryService.addCategory(name, userId);

//         verify(categoryRepository).existsByNameAndUserId(name, userId);
//         verify(categoryRepository).save(any(Category.class));
//         assertEquals(name, result.getName());
//         assertEquals(userId, result.getUserId());
//     }

//     @Test
//     void testAddCategory_throwsException_ifCategoryAlreadyCreated() {
//         String name = "Comédie";
//         Long userId = 1L;
//         when(categoryRepository.existsByNameAndUserId(name, userId)).thenReturn(true);

//         IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                 () -> categoryService.addCategory(name, userId));

//         assertEquals("Catégorie déjà créée", exception.getMessage());
//         verify(categoryRepository).existsByNameAndUserId(name, userId);
//         verify(categoryRepository, never()).save(any());
//     }

//     @Test
//     void testUpdateCategory_updatesAndReturnsCategory() {
//         Long categoryId = 1L;
//         String newName = "Science-fiction";
//         Long userId = 42L;
//         Category existing = new Category("Comédie", userId);
//         when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existing));
//         when(categoryRepository.save(existing)).thenReturn(existing);

//         Category result = categoryService.updateCategory(categoryId, newName);

//         verify(categoryRepository).findById(categoryId);
//         verify(categoryRepository).save(existing);
//         assertEquals(newName, result.getName());
//         assertEquals(userId, result.getUserId());
//     }

//     @Test
//     void testUpdateCategory_throwsException_ifNotFound() {
//         Long categoryId = 99L;
//         when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

//         IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                 () -> categoryService.updateCategory(categoryId, "Science-fiction"));

//         assertEquals("Catégorie non trouvée", exception.getMessage());
//         verify(categoryRepository).findById(categoryId);
//         verify(categoryRepository, never()).save(any());
//     }

//     @Test
//     void testDeleteCategory_successfullyDeletesCategory() {
//         Long categoryId = 5L;
//         when(categoryRepository.existsById(categoryId)).thenReturn(true);

//         categoryService.deleteCategory(categoryId);

//         verify(categoryRepository).existsById(categoryId);
//         verify(categoryRepository).deleteById(categoryId);
//     }

//     @Test
//     void testDeleteCategory_throwsException_whenCategoryNotFound() {
//         Long categoryId = 99L;
//         when(categoryRepository.existsById(categoryId)).thenReturn(false);

//         IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                 () -> categoryService.deleteCategory(categoryId));

//         assertEquals("Catégorie non trouvée", exception.getMessage());
//         verify(categoryRepository).existsById(categoryId);
//         verify(categoryRepository, never()).deleteById(any());
//     }
// }
