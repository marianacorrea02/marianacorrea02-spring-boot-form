package com.springboot.form.app.springbootform.Controller;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.springboot.form.app.springbootform.Editors.NombreMayusculaEditor;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);//evitar valores ambiguos
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(String.class, "name", new NombreMayusculaEditor());
    }

    @ModelAttribute("paises")
    public List<String> paises(){
        return Arrays.asList("Colombia","Argentina","Brazil","Alemania");
    }
    @ModelAttribute("paisesMap")
    public Map<String, String> paisesMap(){
       Map <String, String> paises= new HashMap<String, String>();
       paises.put("COl", "Colombia");
       paises.put("ARG", "Argentina");
       paises.put("BRA", "Brazil");
       paises.put("ALE", "Alemania");
       return paises;
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
