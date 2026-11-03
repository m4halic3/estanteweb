package br.com.estanteweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservaController {

    @PostMapping("/reserva")
    public String postReserva(
        @RequestParam String nome,
        @RequestParam String telefone,
        Model container) {

        if (telefone.isBlank()) {
            container.addAttribute("erro", "O campo telefone deve ser preenchido!");
            container.addAttribute("nome", nome);
            return "reserva";
        }
        return "redirect:/reserva/confirmada";
    }
}
