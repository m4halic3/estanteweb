package br.com.estanteweb;

import java.util.Map;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static Usuario deMap(Map<String, Object> mapa) {
        Usuario usuario = new Usuario();
        usuario.setId(((Number) mapa.get("id")).longValue());
        usuario.setNome((String) mapa.get("nome"));
        usuario.setEmail((String) mapa.get("email"));
        usuario.setSenha((String) mapa.get("senha"));
        return usuario;
    }
}