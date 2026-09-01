package br.com.estanteweb;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class LivroController {

    private LivroRepository repository = new LivroRepository();

    @GetMapping("/livros")
    @ResponseBody
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
    @ResponseBody
    public String totalLivros() {
        return "O acervo tem " + repository.contarLivros() + " livros.";
    }

    @GetMapping("/livros/html")
    public String listarLivrosHtml(Model container) {
        List<Livro> acervo = repository.listarTodos();
        container.addAttribute("livros", acervo);
        return "livros";
    }

    @GetMapping("/livros/busca")
    public ResponseEntity<String> listarLivrosFiltrados(@RequestParam(defaultValue = "999") double precoMaximo) {
        List<Livro> livrosFiltrados = repository.listarAtePreco(precoMaximo);

        if (livrosFiltrados.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("Nenhum livro encontrado com preço até R$ " + precoMaximo);
        }

        String resp = "Livros com preço até R$ " + precoMaximo + ": ";
        for (int i = 0; i < livrosFiltrados.size(); i++) {
            Livro livro = livrosFiltrados.get(i);
            resp += livro.getTitulo() + ", de " + livro.getAutor() + " (R$ " + livro.getPreco() + ")";
            if (i < livrosFiltrados.size() - 1) {
                resp += " | ";
            }
        }

        return ResponseEntity.ok()
                .header("X-Total-Encontrados", String.valueOf(livrosFiltrados.size()))
                .body(resp);
    }

    @GetMapping("/livros/quem-acessa")
    @ResponseBody
    public String quemAcessa(@RequestHeader("User-Agent") String userAgent) {
        return "Você acessou usando o cliente: " + userAgent;
    }
}