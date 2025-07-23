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
// import org.springframework.security.crypto.password.PasswordEncoder;

// import com.dwwm.visiotech.entities.Role;
// import com.dwwm.visiotech.entities.User;
// import com.dwwm.visiotech.repositories.UserRepository;

// @ExtendWith(MockitoExtension.class)
// class AuthServiceTest {

//     @Mock
//     private UserRepository userRepository;

//     @Mock
//     private PasswordEncoder passwordEncoder;

//     @InjectMocks
//     private AuthService authService;

//     @Test
//     void testRegister_successfullyCreatesUser_whenEmailIsNew() {
//         String email = "user@example.com";
//         String rawPassword = "plainPass";
//         String encodedPassword = "$2a$10$hashed";
//         Role role = Role.USER;
//         User saved = new User(email, encodedPassword, role);
//         when(userRepository.existsByEmail(email)).thenReturn(false);
//         when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);
//         when(userRepository.save(any(User.class))).thenReturn(saved);

//         User result = authService.register(email, rawPassword);

//         verify(userRepository).existsByEmail(email);
//         verify(passwordEncoder).encode(rawPassword);
//         verify(userRepository).save(any(User.class));
//         assertEquals(email, result.getEmail());
//         assertEquals(encodedPassword, result.getPassword());
//         assertEquals(role, result.getRole());
//     }

//     @Test
//     void testRegister_throwsException_whenEmailExists() {
//         String email = "existing@example.com";
//         String rawPassword = "plainPass";
//         when(userRepository.existsByEmail(email)).thenReturn(true);

//         IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                 () -> authService.register(email, rawPassword));

//         assertEquals("Email déjà utilisé", exception.getMessage());
//         verify(userRepository).existsByEmail(email);
//         verify(passwordEncoder, never()).encode(any());
//         // ou
//         // verifyNoInteractions(passwordEncoder);
//         verify(userRepository, never()).save(any());
//         // ou
//         // verifyNoMoreInteractions(userRepository);
//     }
// }
