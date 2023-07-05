package org.example.controller;

import jakarta.validation.Valid;
import org.example.domain.User;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Objects;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        if (user.getUsername() == null && user.getUsername().isEmpty()) {
            model.addAttribute("usernameError", "Username cannot be empty");
            return "registration";

        }
        if (!Objects.equals(user.getPassword(), user.getPasswordConfirm())) {
            model.addAttribute("passwordError", "password wrong");
            return "registration";
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists");
            return "registration";
        }

        return "redirect:/login";
    }
}

