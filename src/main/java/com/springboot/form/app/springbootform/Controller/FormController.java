package com.springboot.form.app.springbootform.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.form.app.springbootform.Models.User;

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

        User usuario = new User();
        usuario.setEmail(email);
        usuario.setUsername(username);
        usuario.setPassword(password);

        model.addAttribute("titulo", "Resiltado form");
        model.addAttribute("usuario", usuario);
    

        return "resultados";
    }
}
