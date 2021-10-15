package br.com.zupacademy.adriano.mercadolivre.transacao;

import br.com.zupacademy.adriano.mercadolivre.compra.Compra;
import br.com.zupacademy.adriano.mercadolivre.pergunta.DadosDoEmail;
import br.com.zupacademy.adriano.mercadolivre.pergunta.Mailer;
import org.springframework.stereotype.Service;

@Service
public class EnvioEmailCompraSucesso implements EventoCompraSucesso {


    private final Mailer mailer;

    public EnvioEmailCompraSucesso(Mailer mailer) {
        this.mailer = mailer;
    }

    @Override
    public void processar(Compra compra) {
        String assunto = "Compra concluída com sucesso: " + compra.getProduto().getNome();
        String mensagem = "O seu pedido foi concluído e em breve chegará em sua casa";
        String emailDestino = compra.getUsuario().getEmail();
        DadosDoEmail dadosDoEmail = new DadosDoEmail(mensagem, assunto, emailDestino);
        mailer.enviar(dadosDoEmail);
    }
}
