package br.com.zupacademy.adriano.mercadolivre.transacao;

import br.com.zupacademy.adriano.mercadolivre.compra.Compra;

public interface EventoCompraFalha {
    void processa(Compra compra);
}
