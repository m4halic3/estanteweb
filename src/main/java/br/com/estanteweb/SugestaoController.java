package br.com.estanteweb;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//Aula 03 - missão 4

@Controller
public class SugestaoController {

    private SugestaoRepository repository = new SugestaoRepository();

    @GetMapping(value = "/sugestoes/nova", produces = "text/html")
    @ResponseBody
    public String formulario() {
        return "<form method='post' action='/sugestoes'>" +
               "  <label>Nome: <input type='text' name='nomeCliente' /></label><br/>" +
               "  <label>Livro: <input type='text' name='livroSugerido' /></label><br/>" +
               "  <button type='submit'>Enviar</button>" +
               "</form>";
    }

    @PostMapping("/sugestoes")
    public ResponseEntity<String> receberSugestao(@RequestParam String nomeCliente, @RequestParam String livroSugerido) {
        Sugestao sugestao = new Sugestao(nomeCliente, livroSugerido);
        repository.adicionarSugestao(sugestao);

        int totalAcumulado = repository.listarSugestoes().size();

        return ResponseEntity.status(201)
                .header("X-Total-Sugestoes", String.valueOf(totalAcumulado))
                .body("Sugestão de " + nomeCliente + " para o livro '" + livroSugerido + "' recebida com sucesso!");
    }

    @GetMapping("/sugestoes")
    public String listarSugestoes(Model container) {
        container.addAttribute("sugestoes", repository.listarSugestoes());
        return "sugestoes";
    }
}