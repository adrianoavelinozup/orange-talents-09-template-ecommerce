package br.com.zupacademy.adriano.mercadolivre.pergunta;

import br.com.zupacademy.adriano.mercadolivre.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoDeEmail {
    @Autowired
    private Mailer mailer;

    public void enviarEmailNovaPergunta(Pergunta pergunta) {
        DadosDoEmail dadosDoEmail = new DadosDoEmail(
                "Compra iniciada com sucesso!",
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
