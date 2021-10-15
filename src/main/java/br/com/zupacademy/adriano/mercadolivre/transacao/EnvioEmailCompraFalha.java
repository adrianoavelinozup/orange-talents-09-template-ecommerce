package br.com.zupacademy.adriano.mercadolivre.transacao;

import br.com.zupacademy.adriano.mercadolivre.compra.Compra;
import br.com.zupacademy.adriano.mercadolivre.pergunta.DadosDoEmail;
import br.com.zupacademy.adriano.mercadolivre.pergunta.Mailer;
import org.springframework.stereotype.Service;

@Service
public class EnvioEmailCompraFalha implements EventoCompraFalha {
    private final Mailer mailer;

    public EnvioEmailCompraFalha(Mailer mailer) {
        this.mailer = mailer;
    }

    @Override
    public void processa(Compra compra) {
        String assunto = "Falha no pagamento da compra do produto" + compra.getProduto().getNome();
        String mensagem = "Ocorreu algum problema no seu pagamento";
        String emailDestino = compra.getUsuario().getEmail();
        DadosDoEmail dadosDoEmail = new DadosDoEmail(mensagem, assunto, emailDestino);
        mailer.enviar(dadosDoEmail);
    }
}
