package br.com.estanteweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class TesteController {

    @GetMapping("/ola")
    public String teste(Model container) {
        container.addAttribute("nomeLoja", "EstanteWeb");
        container.addAttribute("anoFundacao", 1998);

        return "ola";
    }
    
}
