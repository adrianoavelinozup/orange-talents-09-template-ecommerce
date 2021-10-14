package br.com.zupacademy.adriano.mercadolivre.pergunta;

import br.com.zupacademy.adriano.mercadolivre.compra.Compra;
import br.com.zupacademy.adriano.mercadolivre.produto.Produto;
import br.com.zupacademy.adriano.mercadolivre.transacao.ClienteNF;
import br.com.zupacademy.adriano.mercadolivre.transacao.ClienteRankingVendedores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class Servicos {
    @Autowired
    private Mailer mailer;

    @Autowired
    private ClienteNF clienteNF;

    @Autowired
    private ClienteRankingVendedores clienteRankingVendedores;

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
    public void enviarEmailCompraConcluida(Compra compra) {
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Olá, " + compra.getUsuario().getEmail())
                .append(", o seu pedido " + compra.getId())
                .append(" fui concluído. Na sua compra existe: " + compra.getQuantidade())
                .append(" " + compra.getProduto().getNome())
        ;

        DadosDoEmail dadosDoEmail = new DadosDoEmail(
                mensagem.toString(),
                "Compra concluída",
                compra.getUsuario().getEmail());
        mailer.enviar(dadosDoEmail);
    }

    public void enviarEmailPgamentoComFalha(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Olá, " + compra.getUsuario().getEmail())
                .append(", não foi possível concluir o seu pagamento no pedido " + compra.getId())
                .append(". Acesse: " + compra.gerarUrlDeRetorno(uriComponentsBuilder))
                .append(" para realizar o seu pagamento")
        ;

        DadosDoEmail dadosDoEmail = new DadosDoEmail(
                mensagem.toString(),
                "Compra - Pagamento com falha",
                compra.getUsuario().getEmail());
        mailer.enviar(dadosDoEmail);
    }

    public void comunicarSistemaNF(Compra compra, String token) {
        long usuarioId = compra.getUsuario().getId();
        clienteNF.enviar(usuarioId, compra.getId(), token);
    }

    public void comunicarSistemaRankingVendedores(Compra compra, String token) {
        long vendedorId = compra.getProduto().getDono().getId();
        clienteRankingVendedores.enviar(vendedorId, compra.getId(), token);
    }
}
