package br.com.zupacademy.adriano.mercadolivre.pergunta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class DadosDoEmail {
    private String mensagem;
    private String assunto;
    private String emailDestino;

    public DadosDoEmail(@NotBlank String mensagem,
                        @NotBlank String assunto,
                        @NotBlank @Email String emailDestino) {
        this.mensagem = mensagem;
        this.assunto = assunto;
        this.emailDestino = emailDestino;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getEmailDestino() {
        return emailDestino;
    }
}
