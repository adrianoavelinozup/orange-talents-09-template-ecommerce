package br.com.zupacademy.adriano.mercadolivre.pergunta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class ServicoDeEmail {
    @Autowired
    private Mailer mailer;

    @Value("${mercadolivre.email.naoresponda}")
    private String from;

    public void enviar(@NotNull @Valid Pergunta pergunta) {
        mailer.enviar("<html>...</html>",
                "Nova pergunta",
                pergunta.getUsuario().getEmail(),
                from,
                pergunta.getEmailDoDonoDoProduto());
    }
}
