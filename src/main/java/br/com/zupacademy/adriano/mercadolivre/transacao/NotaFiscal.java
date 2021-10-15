package br.com.zupacademy.adriano.mercadolivre.transacao;

import br.com.zupacademy.adriano.mercadolivre.compra.Compra;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscal implements EventoCompraSucesso {

    private final ClienteNF clienteNF;

    public NotaFiscal(ClienteNF clienteNF) {
        this.clienteNF = clienteNF;
    }

    @Override
    public void processar(Compra compra) {
//        Assert.isTrue(compra.processadaComSucesso(),"Opa opa opa compra nao concluida com sucesso "+compra);

            long usuarioId = compra.getUsuario().getId();
            clienteNF.enviar(usuarioId, compra.getId());
    }

}