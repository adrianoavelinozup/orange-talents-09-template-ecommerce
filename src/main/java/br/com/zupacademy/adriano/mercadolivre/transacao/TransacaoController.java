package br.com.zupacademy.adriano.mercadolivre.transacao;

import br.com.zupacademy.adriano.mercadolivre.compra.Compra;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
public class TransacaoController {

    private final EntityManager entityManager;
    private final EventosNovaCompra eventosNovaCompra;

    public TransacaoController(EntityManager entityManager, EventosNovaCompra eventosNovaCompra) {
        this.entityManager = entityManager;
        this.eventosNovaCompra = eventosNovaCompra;
    }

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public void processarPagSeguro(@PathVariable("id") Long compraId, @RequestBody @Valid TransacaoRequest transacaoRequest) {
        this.processar(compraId, transacaoRequest);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public void processarPaypal(@PathVariable("id") Long compraId, @RequestBody @Valid TransacaoRequest transacaoRequest) {
        this.processar(compraId, transacaoRequest);
    }

    private  void processar(Long compraId, TransacaoRequest transacaoRequest) {
        Compra compra = entityManager.find(Compra.class, compraId);
        Transacao transacao = transacaoRequest.toModel(compra);
        compra.adicionarTransacao(transacao);
        compra.concluir(transacao);
        entityManager.merge(compra);
        eventosNovaCompra.processar(compra);
    }
}
