package br.com.estanteweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PaginaController {
    // Missão 1.1
    @GetMapping("/")
    public String boasVindas() {
        return "EstanteWeb: Livros usados com história";
    }
    //Missão 1.2
    @GetMapping("/sobre")
    public String sobre (){
        return "Sebo de bairro especializado em livros usados, com um acervo cuidadosamente selecionado";
    }
    //Missão 1.3
    //@GetMapping("/sobre")
    //public String sobreDuplicado() {
        //return "Outra descrição da loja";
    //}
}
