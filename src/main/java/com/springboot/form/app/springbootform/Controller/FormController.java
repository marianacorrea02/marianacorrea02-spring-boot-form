package com.springboot.form.app.springbootform.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    // Mostrar Formulario en la pantalla
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("titulo", "Formulario usuarios");
        return "form";
    }

    // Obtiene los datos del formulario
    @PostMapping("/form")
    public String procesar(Model model,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email) {

        model.addAttribute("titulo", "Resiltado form");
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("email", email);

        return "resultados";
    }
}
