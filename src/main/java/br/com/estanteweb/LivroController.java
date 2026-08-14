package br.com.estanteweb;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivroController {

    private LivroRepository repository = new LivroRepository();

    @GetMapping("/livros")
    public String listarLivros() {
        List<Livro> acervo = repository.listarTodos();

        String resposta = "Acervo EstanteWeb: ";
        for (int i = 0; i < acervo.size(); i++) {
            Livro livro = acervo.get(i);
            resposta += livro.getTitulo() + ", de " + livro.getAutor() + " (R$ " + livro.getPreco() + ")";
            if (i < acervo.size() - 1) {
                resposta += " | ";
            }
        }

        return resposta;
    }

    @GetMapping("/livros/total")
    public String totalLivros() {
        return "O acervo tem " + repository.contarLivros() + " livros.";
    }

    @GetMapping(value = "/livros/html", produces = "text/html")
    public String listarLivrosHtml() {
        List<Livro> acervo = repository.listarTodos();

        StringBuilder html = new StringBuilder();
        html.append("<ul>");
        for (Livro livro : acervo) {
            html.append("<li>")
                .append(livro.getTitulo())
                .append(", de ")
                .append(livro.getAutor())
                .append(" (R$ ")
                .append(livro.getPreco())
                .append(")</li>");
        }
        html.append("</ul>");

        return html.toString();
    }
}