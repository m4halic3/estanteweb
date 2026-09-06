package br.com.estanteweb;

import java.util.ArrayList;
import java.util.List;

public class SugestaoRepository {
    private List<Sugestao> sugestoes;

    public SugestaoRepository() {
        this.sugestoes = new ArrayList<>();
    }

    public void adicionarSugestao(Sugestao sugestao) {
        this.sugestoes.add(sugestao);
    }

    public List<Sugestao> listarSugestoes() {
        return this.sugestoes;
    }
}