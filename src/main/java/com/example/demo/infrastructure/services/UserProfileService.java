package com.example.demo.infrastructure.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.schemas.UserEntity;
import com.example.demo.domain.schemas.UserImageEntity;
import com.example.demo.infrastructure.repositories.UserImageRepository;
import com.example.demo.infrastructure.repositories.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.core.io.ClassPathResource;

@Service
public class UserProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserImageRepository userImageRepository;

    private byte[] getUserImage(long userId) {
        // Crear un objeto UserImageEntity con el userId que buscamos
        // llego a get userImage
        System.out.println("llego a get userImage");
        UserImageEntity userImage = userImageRepository.findByUser_Id(userId)
                .orElse(null);

        if (userImage != null) {
            return userImage.getImage(); // Devuelve la imagen si la encuentra
        } else {
            return getDefaultImage(); // Devuelve una imagen por defecto si no hay imagen del usuario
        }
    }

    private byte[] getDefaultImage() {
        ClassPathResource resource = new ClassPathResource("static/images/perfil.jpg");

        try (InputStream inputStream = resource.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            // Si ocurre un error al leer el archivo, podemos manejarlo (por ejemplo,
            // loguearlo)
            e.printStackTrace();
            return new byte[0]; // Devuelve un arreglo vacío o alguna otra imagen por defecto en caso de error
        }
    }

    public ResponseEntity<byte[]> getImage(long id) {
        // usa los otros metodos para traer la imagen y ponerla en la response

        System.out.println("llego seervicio");

        byte[] imageBytes = getUserImage(id);
        if (imageBytes.length == 0) {
            return ResponseEntity.notFound().build(); // Si no se encuentra la imagen, devuelve un 404
        }

        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg") // Cambia el tipo de contenido según la imagen
                .body(imageBytes);
    }

    // Obtener el perfil de usuario
    public UserEntity getUser(long x) {
        return userRepository.findById(x).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Guardar la imagen de perfil
    public void saveProfileImage(MultipartFile file, long x) {
        try {
            byte[] imageBytes = file.getBytes();
            UserEntity user = getUser(x); // Asegúrate de que este método cargue la imagen

            // Verificar si el usuario ya tiene una imagen
            if (user.getUserImage() != null) {
                // Actualizar la imagen existente
                UserImageEntity existingImage = user.getUserImage();
                existingImage.setImage(imageBytes);
            } else {
                // Crear nueva imagen si no existe
                UserImageEntity newImage = new UserImageEntity();
                newImage.setImage(imageBytes);
                newImage.setUser(user);
                user.setUserImage(newImage);
            }

            userRepository.save(user); // Guarda el usuario (propaga los cambios a la imagen)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}