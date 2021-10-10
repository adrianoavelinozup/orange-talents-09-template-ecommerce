package br.com.zupacademy.adriano.mercadolivre.pergunta;

import org.springframework.stereotype.Component;

@Component
public class EnviadorDeEmailDev implements ServicoDeEmail {
    @Override
    public void enviar(Pergunta pergunta) {
        System.out.println("===========================================");
        System.out.println("Enviando email ...");
        System.out.println("===========================================");
    }
}
