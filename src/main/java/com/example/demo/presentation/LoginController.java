package com.example.demo.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.schemas.UserEntity;
import com.example.demo.infrastructure.services.UserService;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    // Mostrar la página de login
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Manejar el formulario de login
    @PostMapping("/login")
    public String handleLogin(@RequestParam String email, @RequestParam String password,Model model) {
        UserEntity user = userService.findByEmail(email);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("User: " + user);
        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/dashboard/"+user.getId(); // Redirige a la página principal (o al dashboard)
        } else {
            // Si el usuario no está registrado o la contraseña no es correcta, redirige a
            // la página de registro
            model.addAttribute("loginError", "Correo o contraseña incorrectos.");
            //dos segundos despues elimina el error
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //aquita el atributo
            model.addAttribute("loginError", null);
            return "login";
        }
    }
}
