package br.com.estanteweb;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
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

    // Missão 1 e Missão 2 unificadas: busca com parâmetro opcional, ResponseEntity, 404 se vazio e cabeçalho customizado
    @GetMapping("/livros/busca")
    public ResponseEntity<String> listarLivrosFiltrados(@RequestParam(defaultValue = "999") double precoMaximo) {
        List<Livro> livrosFiltrados = repository.listarAtePreco(precoMaximo);

        // Missão 2.1: Se não encontrar nenhum livro, devolve status 404
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

        // Resposta da pergunta da missão 1:
        // Esse comportamento é decidido pelo Spring Boot (mais especificamente pelo conversor 
        // de tipos do framework). O fato de o parâmetro no seu método ser do tipo double 
        // força o Spring a tentar converter a String "abc" para número. Como a conversão 
        // falha, o próprio framework barra a execução e retorna um status 400 Bad Request, 
        // mostrando que a tipagem estrita protege o método de receber lixo.

        // Missão 2.2: Retorna 200 OK com o cabeçalho X-Total-Encontrados
        return ResponseEntity.ok()
                .header("X-Total-Encontrados", String.valueOf(livrosFiltrados.size()))
                .body(resp);
    }

    // Missão 2.3: Rota que lê o cabeçalho User-Agent
    @GetMapping("/livros/quem-acessa")
    public String quemAcessa(@RequestHeader("User-Agent") String userAgent) {
        return "Você acessou usando o cliente: " + userAgent;
    }

    // Resposta à pergunta presente na atividade:
    //Faz sentido devolver 404 (Not Found) porque o recurso solicitado com aqueles critérios 
    //específicos não foi encontrado no servidor, o que é semanticamente mais correto do que 
    //retornar um 200 OK com uma lista vazia, ajudando quem consome a API a entender que a 
    //busca não gerou resultados.
}