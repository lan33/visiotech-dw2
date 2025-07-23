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
// import org.springframework.security.crypto.password.PasswordEncoder;

// import com.dwwm.visiotech.entities.Role;
// import com.dwwm.visiotech.entities.User;
// import com.dwwm.visiotech.repositories.UserRepository;

// @ExtendWith(MockitoExtension.class)
// class UserServiceTest {

//     @Mock
//     private UserRepository userRepository;

//     @Mock
//     private PasswordEncoder passwordEncoder;

//     @InjectMocks
//     private UserService userService;

//     @Test
//     void testUpdatePassword_updatesAndReturnsUser() {
//         Long userId = 1L;
//         String newPassword = "secret123";
//         String encodedNewPassword = "$2a$10$hashedpass";
//         User existing = new User("test@example.com", "oldpass", Role.USER);
//         when(userRepository.findById(userId)).thenReturn(Optional.of(existing));
//         when(passwordEncoder.encode(newPassword)).thenReturn(encodedNewPassword);
//         when(userRepository.save(existing)).thenReturn(existing);

//         User result = userService.updatePassword(userId, newPassword);

//         verify(userRepository).findById(userId);
//         verify(passwordEncoder).encode(newPassword);
//         verify(userRepository).save(existing);
//         assertEquals(encodedNewPassword, result.getPassword());
//     }

//     @Test
//     void testUpdatePassword_throwsException_whenUserNotFound() {
//         Long userId = 99L;
//         when(userRepository.findById(userId)).thenReturn(Optional.empty());

//         IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                 () -> userService.updatePassword(userId, "secret"));

//         assertEquals("Utilisateur non trouvé", exception.getMessage());
//         verify(userRepository).findById(userId);
//         verify(passwordEncoder, never()).encode(any());
//         verify(userRepository, never()).save(any());
//     }
// }
