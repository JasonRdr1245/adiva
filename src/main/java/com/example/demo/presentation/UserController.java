package com.example.demo.presentation;

import com.example.demo.domain.schemas.UserEntity;
import com.example.demo.infrastructure.repositories.AdvisorRepository;
import com.example.demo.infrastructure.repositories.UserRepository;
import com.example.demo.domain.enums.AdvisorType;
import com.example.demo.domain.enums.UserType;
import com.example.demo.domain.models.UserAdvisorForm;
import com.example.demo.domain.schemas.AdvisorEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final AdvisorRepository advisorRepository;

    public UserController(UserRepository userRepository, AdvisorRepository advisorRepository) {
        this.userRepository = userRepository;
        this.advisorRepository = advisorRepository;
    }

    @GetMapping("/register")
    public String showFormIndex(Model model) {
        model.addAttribute("user", new UserEntity(
                UserType.USER));
        return "index";
    }

    @GetMapping("/register-user")
    public String showForm(Model model) {
        model.addAttribute("user", new UserEntity(
                UserType.USER));
        return "usuario";
    }

    @PostMapping("/register-user")
    public String registerUser(@ModelAttribute UserEntity user) {
        user.setType(UserType.USER);
        user.setAdvisorEntity(null); // aseguramos que no haya info extra
        userRepository.save(user);
        return "redirect:/register-user?success";
    }

    @PostMapping("/register-advisor")
    public String registerAdvisor(@ModelAttribute UserAdvisorForm userAdvisorForm) {
        UserEntity user = userAdvisorForm.getUserEntity();
        AdvisorEntity advisorEntity = userAdvisorForm.getAdvisorEntity();
        System.out.println("UserAdvisorForm: " + userAdvisorForm);
        System.out.println("User: " + user);
        System.out.println("Advisor: " + advisorEntity);

        // Asociar ambos objetos
        user.setType(UserType.ADVISOR);
        advisorEntity.setUserEntity(user);
        user.setAdvisorEntity(advisorEntity);

        userRepository.save(user); // Gracias a CascadeType.ALL, también guarda el advisor

        return "redirect:/register-advisor?success";
    }

    @GetMapping("/register-advisor")
    public String showFormAdvisor(Model model) {
        UserAdvisorForm form = new UserAdvisorForm();
        form.setUserEntity(new UserEntity(UserType.ADVISOR)); // inicializar el UserEntity
        form.setAdvisorEntity(new AdvisorEntity()); // inicializar el AdvisorEntity
        System.out.println("Form: " + form);
        model.addAttribute("userAdvisorForm", form); // Pasar el objeto compuesto al modelo
        return "consejero";
    }

}