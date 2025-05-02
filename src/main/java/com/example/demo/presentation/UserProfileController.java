package com.example.demo.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.infrastructure.services.UserProfileService;

@Controller
public class UserProfileController {

    private UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    // Método para cargar la página de perfil
    @GetMapping("/profile-image/{userId}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable long userId) {
        System.out.println("UserId: " + userId);
        return userProfileService.getImage(userId);
    }

    // Endpoint para cargar la imagen de perfil
    @PostMapping("/upload-image/{userId}")
    public String uploadProfileImage(@RequestParam("profileImage") MultipartFile file, @PathVariable long userId) {
        // Guardar la imagen en la base de datos
        userProfileService.saveProfileImage(file, userId);
        return "redirect:/dashboard/" + userId; // Redirige al dashboard del usuario
    }
}