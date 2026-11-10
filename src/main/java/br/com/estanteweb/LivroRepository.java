package br.com.estanteweb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LivroRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // colunas que podem ser usadas no ORDER BY (missão 4)
    private static final List<String> COLUNAS_PERMITIDAS = List.of("titulo", "autor", "ano", "preco");

    public List<Livro> listarAtePreco(double precoMaximo) {
        String sql = "SELECT * FROM livro WHERE preco <= ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, precoMaximo);
        return converter(linhas);
    }

    public List<Livro> listarTodos() {
        String sql = "SELECT * FROM livro";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql);
        return converter(linhas);
    }

    public int contarLivros() {
        String sql = "SELECT COUNT(*) FROM livro";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Double somarValorAcervo() {
        String sql = "SELECT SUM(preco) FROM livro";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public void salvar(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, ano, preco) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, livro.getTitulo(), livro.getAutor(), livro.getAno(), livro.getPreco());
    }

    public void atualizar(Livro livro) {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, ano = ?, preco = ? WHERE id = ?";
        jdbcTemplate.update(sql, livro.getTitulo(), livro.getAutor(), livro.getAno(), livro.getPreco(), livro.getId());
    }

    public Livro buscarPorId(Long id) {
        String sql = "SELECT * FROM livro WHERE id = ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, id);
        if (linhas.isEmpty()) {
            return null;
        }
        return Livro.deMap(linhas.get(0));
    }

   
    
    public List<Livro> listarOrdenadoPor(String coluna) {
        if (coluna == null || !COLUNAS_PERMITIDAS.contains(coluna)) {
            return listarTodos();
        }
        String sql = "SELECT * FROM livro ORDER BY " + coluna;
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql);
        return converter(linhas);
    }

    public List<Livro> listarDoMesmoAutor(String autor, Long idAtual) {
        String sql = "SELECT * FROM livro WHERE autor = ? AND id <> ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, autor, idAtual);
        return converter(linhas);
    }

    private List<Livro> converter(List<Map<String, Object>> linhas) {
        List<Livro> livros = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            livros.add(Livro.deMap(linha));
        }
        return livros;
    }
}