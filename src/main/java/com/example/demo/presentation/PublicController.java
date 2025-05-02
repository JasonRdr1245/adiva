package com.example.demo.presentation;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.schemas.PublicEntity;
import com.example.demo.domain.schemas.UserEntity;
import com.example.demo.infrastructure.repositories.UserRepository;
import com.example.demo.infrastructure.services.PublicEntityService;

@Controller
@RequestMapping("/public")
public class PublicController {
    private final PublicEntityService publicEntityService;
    private final UserRepository userRepository;

    public PublicController(PublicEntityService publicEntityService, UserRepository userRepository) {
        this.publicEntityService = publicEntityService;
        this.userRepository = userRepository;
    }

    // 1. GET para listar todos
    @GetMapping
    public String listPublicEntities(Model model, @RequestParam(required = true) Long userId) {
        model.addAttribute("publicEntities", publicEntityService.getAll(userId));
        return "public/list"; // Thymeleaf template public/list.html
    }

    // 2. GET para mostrar el formulario de subida
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("publicEntity", new PublicEntity());
        return "public/form"; // Thymeleaf template public/form.html
    }

    // 3. POST para subir una imagen
    @PostMapping("/save/{userId}")
    public String savePublicEntity(
            @ModelAttribute PublicEntity publicEntity,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam(value = "comments", defaultValue = "") String comments, // Obtener comentarios del formulario
            @PathVariable Long userId,
            RedirectAttributes redirectAttributes) throws IOException {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Inicializar campos obligatorios
        // agrega el image file como bytes
        publicEntity.setImage(imageFile.getBytes());
        publicEntity.setUserOwned(user);
        publicEntity.setDate(LocalDateTime.now().toString());
        publicEntity.setLikes(new Long(0));
        publicEntity.setType("POST"); // o el tipo que corresponda
        // saca los comments
        publicEntity.setComments(comments);
        System.out.println("PublicEntity: " + publicEntity);
        try {
            publicEntityService.save(publicEntity, imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al subir la imagen");
            return "redirect:/user/profile";
        }

        return "redirect:/dashboard/" + userId;
    }

    // 4. DELETE para eliminar
    @GetMapping("/delete/{id}")
    public String deletePublicEntity(@PathVariable Long id) {
        publicEntityService.delete(id);
        return "redirect:/public";
    }
}
