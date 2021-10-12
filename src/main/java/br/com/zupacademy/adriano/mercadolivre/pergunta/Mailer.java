package br.com.zupacademy.adriano.mercadolivre.pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface Mailer {
    void enviar(@NotNull @Valid DadosDoEmail dadosDoEmail);
}
