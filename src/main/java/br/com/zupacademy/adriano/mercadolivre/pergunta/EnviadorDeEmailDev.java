package br.com.zupacademy.adriano.mercadolivre.pergunta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
public class EnviadorDeEmailDev implements Mailer {

    @Value("${mercadolivre.remetente.email}")
    @NotBlank
    private String emailRemetente;

    @Value("${mercadolivre.remetente.nome}")
    @NotBlank
    private String nomeRemetente;

    @Override
    public void enviar(@NotNull @Valid DadosDoEmail dadosDoEmail) {
        System.out.println("Assunto: " + dadosDoEmail.getAssunto());
        System.out.println("Corpo da mensagem: " + dadosDoEmail.getMensagem());
        System.out.println("Nome do remetente: " + nomeRemetente);
        System.out.println("Email de origem: " + emailRemetente);
        System.out.println("Email de destino: " + dadosDoEmail.getEmailDestino());
    }
}
