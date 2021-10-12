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

//    @Value("${mercadolivre.email.naoresponda}")
//    private String from;

    public void enviar(DadosDoEmail dadosDoEmail) {
        mailer.enviar(dadosDoEmail);
    }


}
