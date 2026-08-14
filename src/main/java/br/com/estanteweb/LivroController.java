package br.com.estanteweb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivroController {
    
    @GetMapping("/livros")
    public String listarLivros () {
        List <Livro> acervo = new ArrayList<>();
        acervo.add(new Livro("Dom Casmurro", "Machado de Assis", 1899, 29.90));
        acervo.add(new Livro("1984", "George Orwell", 1949, 29.90));
        acervo.add(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943, 19.90));
        acervo.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, 49.90));
        acervo.add(new Livro("O Hobbit", "J.R.R. Tolkien", 1937, 39.90));

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
    public String totalLivros (){
        List <Livro> acervo = new ArrayList<>();
        acervo.add(new Livro("Dom Casmurro", "Machado de Assis", 1899, 29.90));
        acervo.add(new Livro("1984", "George Orwell", 1949, 29.90));
        acervo.add(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943, 19.90));
        acervo.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, 49.90));
        acervo.add(new Livro("O Hobbit", "J.R.R. Tolkien", 1937, 39.90));

        int totalLivros = acervo.size();
        return "O acervo tem " + totalLivros + " livros.";
    }
}
