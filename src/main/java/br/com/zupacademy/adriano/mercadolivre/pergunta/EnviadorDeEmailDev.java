package br.com.zupacademy.adriano.mercadolivre.pergunta;

import org.springframework.stereotype.Component;

@Component
public class EnviadorDeEmailDev implements Mailer {

    @Override
    public void enviar(String corpoDaMensagem,
                       String assunto,
                       String nomeOrigem,
                       String emailOrigem,
                       String emailDestino) {
        System.out.println("Assunto: " + assunto);
        System.out.println("Corpo da mensagem: " + corpoDaMensagem);
        System.out.println("Nome do remetente: " + nomeOrigem);
        System.out.println("Email de origem: " + emailOrigem);
        System.out.println("Email de destino: " + emailDestino);
    }
}
