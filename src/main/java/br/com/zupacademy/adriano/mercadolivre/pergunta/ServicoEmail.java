package br.com.zupacademy.adriano.mercadolivre.pergunta;

import br.com.zupacademy.adriano.mercadolivre.produto.Produto;
import org.springframework.stereotype.Service;

@Service
public class ServicoEmail {
    private final Mailer mailer;

    public ServicoEmail(Mailer mailer) {
        this.mailer = mailer;
    }

    public void enviarEmailNovaPergunta(Pergunta pergunta) {
        DadosDoEmail dadosDoEmail = new DadosDoEmail(
                "Você recebeu uma nova pergunta",
                 "Nova Pergunta: " + pergunta.getTitulo(),
                pergunta.getEmailDoDonoDoProduto());
        mailer.enviar(dadosDoEmail);
    }

    public void enviarEmailNovaCompra(Produto produto) {
        DadosDoEmail dadosDoEmail = new DadosDoEmail(
                "Compra iniciada com sucesso!",
                "Compra iniciada",
                produto.getDono().getEmail());
        mailer.enviar(dadosDoEmail);
    }
}
