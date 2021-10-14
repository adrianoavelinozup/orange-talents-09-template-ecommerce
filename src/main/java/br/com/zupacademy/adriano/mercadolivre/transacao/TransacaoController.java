package br.com.zupacademy.adriano.mercadolivre.transacao;

import br.com.zupacademy.adriano.mercadolivre.compra.Compra;
import br.com.zupacademy.adriano.mercadolivre.pergunta.Servicos;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final EntityManager entityManager;
    private final Servicos servicos;

    public TransacaoController(EntityManager entityManager, Servicos servicos) {
        this.entityManager = entityManager;
        this.servicos = servicos;
    }

    @PostMapping
    @Transactional
    public void adicionar(@RequestBody @Valid TransacaoRequest transacaoRequest,
                          UriComponentsBuilder uriComponentsBuilder,
                          HttpServletRequest request) {
        Transacao transacao = transacaoRequest.toModel(entityManager);
        Compra compra = transacao.getCompra();
        compra.adicionarTransacao(transacao);

        if (transacao.isTransacaoComSucesso()) {
            compra.concluirCompra(transacao);
            entityManager.merge(compra);
            servicos.comunicarSistemaNF(compra, request.getHeader("Authorization"));
            servicos.comunicarSistemaRankingVendedores(compra, request.getHeader("Authorization"));
            servicos.enviarEmailCompraConcluida(compra);
            return;
        }
        servicos.enviarEmailPgamentoComFalha(compra, uriComponentsBuilder);
    }
}
