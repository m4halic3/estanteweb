package br.com.estanteweb;

import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    private List<Livro> acervo = new ArrayList<>();

    public LivroRepository() {
        acervo.add(new Livro("Dom Casmurro", "Machado de Assis", 1899, 29.90));
        acervo.add(new Livro("1984", "George Orwell", 1949, 29.90));
        acervo.add(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943, 19.90));
        acervo.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, 49.90));
        acervo.add(new Livro("O Hobbit", "J.R.R. Tolkien", 1937, 39.90));
    }

    public List<Livro> listarTodos() {
        return acervo;
    }

    public int contarLivros() {
        return acervo.size();
    }
}