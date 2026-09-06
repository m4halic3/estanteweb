package br.com.estanteweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaginaController {

    // Missão 1.1: @ResponseBody para manter texto cru em / e /sobre
    @GetMapping("/")
    @ResponseBody
    public String boasVindas() {
        return "EstanteWeb: Livros usados com história";
    }

    @GetMapping("/sobre")
    @ResponseBody
    public String sobre() {
        return "Sebo de bairro especializado em livros usados, com um acervo cuidadosamente selecionado";
    }

    // Missão 1.3: GET /contato agora usa Model e retorna o nome da view
    @GetMapping("/contato")
    public String contato(Model model) {
        return "contato";
    }

    // Missão 2.1 e 3.2: POST /contato com validação "grudenta" + redirect no sucesso
    @PostMapping("/contato")
    public String receberContato(
            @RequestParam String nome,
            @RequestParam String mensagem,
            Model model) {

        if (mensagem == null || mensagem.trim().isEmpty()) {
            model.addAttribute("erro", "A mensagem não pode estar em branco.");
            model.addAttribute("nome", nome);
            return "contato";
        }

        return "redirect:/contato/sucesso";
    }

    // Missão 3.1: rota de confirmação
    @GetMapping("/contato/sucesso")
    public String contatoSucesso() {
        return "contato-sucesso";
    }
}