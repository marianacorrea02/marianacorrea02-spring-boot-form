package com.springboot.form.app.springbootform.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.springboot.form.app.springbootform.Models.User;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("user")
public class FormController {

    // Mostrar Formulario en la pantalla
    @GetMapping("/form")
    public String form(Model model) {
        User usuario = new User();
        usuario.setId("3574-35-M1-1D");
        usuario.setName("Luz");
        usuario.setLastname("Pinto");
        model.addAttribute("titulo", "Formulario usuarios");
        model.addAttribute("user", usuario);
        return "form";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(false);//evitar valores ambiguos
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    // Obtiene los datos del formulario
    @PostMapping("/form")
    public String procesar(@Valid User usuario,BindingResult result, Model model, SessionStatus status) {

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
        
    
        status.setComplete();
        return "resultados";
    }
}
