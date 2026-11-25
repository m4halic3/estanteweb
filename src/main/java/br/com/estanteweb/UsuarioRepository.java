package br.com.estanteweb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Usuario buscarPorEmail(String email) {
        List<Map<String, Object>> resultado = jdbcTemplate.queryForList(
                "SELECT * FROM usuario WHERE email = ?", email);

        if (resultado.isEmpty()) {
            return null;
        }
        return Usuario.deMap(resultado.get(0));
    }
}