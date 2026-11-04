package br.com.estanteweb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class LivroController {

    @Autowired
    private LivroRepository repository;

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

    @GetMapping("/livros/valor-total")
    @ResponseBody
    public String valorTotal() {
        return "O valor total do acervo é R$ " + repository.somarValorAcervo();
    }

    @GetMapping("/livros/html")
    public String listarLivrosHtml(@RequestParam(required = false) String ordem, Model container) {
        List<Livro> acervo = repository.listarOrdenadoPor(ordem);
        container.addAttribute("livros", acervo);
        return "livros";
    }

    @GetMapping("/livros/novo")
    public String novoLivro() {
        return "livro-novo";
    }

    @PostMapping("/livros")
    public String salvarLivro(@RequestParam String titulo, @RequestParam String autor,
                              @RequestParam int ano, @RequestParam double preco,
                              Model container) {
        String erro = null;
        if (titulo.isBlank()) {
            erro = "O título é obrigatório.";
        } else if (autor.isBlank()) {
            erro = "O autor é obrigatório.";
        } else if (preco <= 0) {
            erro = "O preço deve ser maior que zero.";
        }

        if (erro != null) {
            container.addAttribute("erro", erro);
            container.addAttribute("titulo", titulo);
            container.addAttribute("autor", autor);
            container.addAttribute("ano", ano);
            container.addAttribute("preco", preco);
            return "livro-novo";
        }

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAno(ano);
        livro.setPreco(preco);

        repository.salvar(livro);
        return "redirect:/livros/html";
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

    @GetMapping("/livros/{id}")
    public String detalheLivro(@PathVariable Long id, Model container) {
        Livro livro = repository.buscarPorId(id);

        if (livro == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        container.addAttribute("livro", livro);

        container.addAttribute("outros", repository.listarDoMesmoAutor(livro.getAutor(), id));

        return "livro";
    }
}