package com.example.demo.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.enums.UserType;
import com.example.demo.infrastructure.repositories.UserRepository;
import com.example.demo.infrastructure.services.PublicEntityService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DashboardController {
    private final UserRepository userRepository;
    private final PublicEntityService publicEntityService;

    public DashboardController(UserRepository userRepositor, PublicEntityService publicEntityService) {
        this.userRepository = userRepositor;
        this.publicEntityService = publicEntityService;
    }

    @GetMapping("/dashboard/{id}")
    public String showDashboard(@PathVariable Long id, Model model) {
        // Guardar el id en el modelo
        model.addAttribute("userId", id); // Ahora puedes usar el id en la plantilla
        userRepository.findById(id).ifPresentOrElse(user -> {
            model.addAttribute("user", user);
            model.addAttribute("isAdvisor", user.getType() == UserType.ADVISOR);
        }, () -> {
            model.addAttribute("error", "Usuario no encontrado");
        });

        model.addAttribute("publicEntities", publicEntityService.getAll(id));
        // Aquí podrías llamar algún servicio para obtener más información del usuario,
        // busca el usuario en el repositorio y lo guardas en el modelo
        // si lo necesitas
        return "dashboard"; // Esto buscará la plantilla "dashboard.html" (o el archivo correspondiente)
    }

    // eliminar usuario desde aqui

    // cerrar session
    @PostMapping("/logout")
    public String logout() {
        // Aquí puedes agregar la lógica para cerrar sesión, como invalidar el token o
        // limpiar la sesión
        return "redirect:/login"; // Redirige a la página de login después de cerrar sesión
    }

    @PostMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        // Eliminar manualmente la sesión

        userRepository.deleteById(id);
        return "redirect:/login?accountDeleted";
    }
}