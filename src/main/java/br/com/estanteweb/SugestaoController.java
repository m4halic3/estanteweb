package br.com.estanteweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SugestaoController {

    @Autowired
    private SugestaoRepository repository;

    @GetMapping("/sugestoes/nova")
    public String formulario(Model model) {
        return "sugestao-nova";
    }

    @PostMapping("/sugestoes")
    public String receberSugestao(
            @RequestParam String nomeCliente,
            @RequestParam String livroSugerido,
            Model model) {

        if (nomeCliente == null || nomeCliente.isBlank()
                || livroSugerido == null || livroSugerido.isBlank()) {
            model.addAttribute("erro", "Preencha seu nome e o livro sugerido.");
            model.addAttribute("nomeCliente", nomeCliente);
            model.addAttribute("livroSugerido", livroSugerido);
            return "sugestao-nova";
        }

        Sugestao sugestao = new Sugestao(nomeCliente, livroSugerido);
        repository.salvar(sugestao);

        return "redirect:/sugestoes";
    }

    @GetMapping("/sugestoes")
    public String listarSugestoes(Model model) {
        model.addAttribute("sugestoes", repository.listarTodas());
        return "sugestoes";
    }
}