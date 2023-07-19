package com.springboot.form.app.springbootform.Controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.form.app.springbootform.Models.User;

import jakarta.validation.Valid;

@Controller
public class FormController {

    // Mostrar Formulario en la pantalla
    @GetMapping("/form")
    public String form(Model model) {
        User usuario = new User();
        model.addAttribute("titulo", "Formulario usuarios");
        model.addAttribute("user", usuario);
        return "form";
    }

    // Obtiene los datos del formulario
    @PostMapping("/form")
    public String procesar(@Valid User usuario,BindingResult result, Model model) {

        model.addAttribute("titulo", "Resultado form");
        if(result.hasErrors()){
            /*Map<String, String> errores = new HashMap<>();
            //poblar la lista
            result.getFieldErrors().forEach(err ->{
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
            });
            model.addAttribute("error", errores);*/
            return "form";
        }
       
        model.addAttribute("titulo", "Resultado form");
        model.addAttribute("usuario", usuario);
        
    

        return "resultados";
    }
}
