package br.com.zupacademy.adriano.mercadolivre.transacao;

import br.com.zupacademy.adriano.mercadolivre.compra.Compra;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaCompra {
    private final Set<EventoCompraSucesso> eventosCompraSucesso;
    private final Set<EventoCompraFalha> eventosCompraFalha;

    public EventosNovaCompra(Set<EventoCompraSucesso> eventosCompraSucesso, Set<EventoCompraFalha> eventosCompraFalha) {
        this.eventosCompraSucesso = eventosCompraSucesso;
        this.eventosCompraFalha = eventosCompraFalha;
    }

    public void processar(Compra compra) {
        if(compra.processadaComSucesso()) {
            eventosCompraSucesso.forEach(evento -> evento.processar(compra));
            return;
        }
        eventosCompraFalha.forEach(evento -> evento.processa(compra));
    }
}
