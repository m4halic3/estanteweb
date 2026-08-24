package br.com.estanteweb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaginaController {

    // Missão anterior
    @GetMapping("/")
    public String boasVindas() {
        return "EstanteWeb: Livros usados com história";
    }

    // Missão anterior
    @GetMapping("/sobre")
    public String sobre() {
        return "Sebo de bairro especializado em livros usados, com um acervo cuidadosamente selecionado";
    }

    //Aula 03 - Missão 3
    // 3.1
    @GetMapping(value = "/contato", produces = "text/html")
    public String contato() {
        return "<form method='post' action='/contato'>" +
               "  <label>Nome: <input type='text' name='nome' /></label><br/>" +
               "  <label>Mensagem: <input type='text' name='mensagem' /></label><br/>" +
               "  <button type='submit'>Enviar</button>" +
               "</form>";
    }

    // 3.2 e 3.3: 
    @PostMapping("/contato")
    public ResponseEntity<String> receberContato(
            @RequestParam String nome, 
            @RequestParam String mensagem) {
        
        // 3.3
        if (mensagem == null || mensagem.trim().isEmpty()) {
            return ResponseEntity.status(400).body("Erro: A mensagem não pode estar em branco.");
        }
        
        return ResponseEntity.ok("Obrigado, " + nome + "! Sua mensagem foi recebida.");
    }

    //Resposta da atividade: Ao enviar o formulário, o navegador me levou para uma nova página com a resposta do servidor sem precisar de JavaScript porque os atributos method="post" e action="/contato" na tag <form> instruem nativamente o navegador a realizar essa submissão HTTP.
}