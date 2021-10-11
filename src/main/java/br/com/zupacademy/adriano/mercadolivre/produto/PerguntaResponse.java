package br.com.zupacademy.adriano.mercadolivre.produto;

import br.com.zupacademy.adriano.mercadolivre.pergunta.Pergunta;

public class PerguntaResponse {
    private String titulo;

    public PerguntaResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
    }

    public String getTitulo() {
        return titulo;
    }
}
