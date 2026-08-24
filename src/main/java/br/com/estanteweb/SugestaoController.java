package br.com.estanteweb;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//Aula 03 - missão 4

@RestController
public class SugestaoController {

    private SugestaoRepository repository = new SugestaoRepository();

    @GetMapping(value = "/sugestoes/nova", produces = "text/html")
    public String formulario() {
        return "<form method='post' action='/sugestoes'>" +
               "  <label>Nome: <input type='text' name='nomeCliente' /></label><br/>" +
               "  <label>Livro: <input type='text' name='livroSugerido' /></label><br/>" +
               "  <button type='submit'>Enviar</button>" +
               "</form>";
    }

    // 4.3
    @PostMapping("/sugestoes")
    public ResponseEntity<String> receberSugestao(@RequestParam String nomeCliente, @RequestParam String livroSugerido) {
        Sugestao sugestao = new Sugestao(nomeCliente, livroSugerido);
        repository.adicionarSugestao(sugestao);

        int totalAcumulado = repository.listarSugestoes().size();

        return ResponseEntity.status(201)
                .header("X-Total-Sugestoes", String.valueOf(totalAcumulado))
                .body("Sugestão de " + nomeCliente + " para o livro '" + livroSugerido + "' recebida com sucesso!");
    }

    // 4.4
    @GetMapping(value = "/sugestoes", produces = "text/html")
    public String listarSugestoes() {
        StringBuilder html = new StringBuilder();
        html.append("<ul>");
        for (Sugestao sugestao : repository.listarSugestoes()) {
            html.append("<li>")
                .append(sugestao.getNomeCliente())
                .append(" sugeriu o livro '")
                .append(sugestao.getLivroSugerido())
                .append("'</li>");
        }
        html.append("</ul>");

        return html.toString();
    }
}