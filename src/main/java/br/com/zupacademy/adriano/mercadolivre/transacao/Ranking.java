package br.com.zupacademy.adriano.mercadolivre.transacao;

import br.com.zupacademy.adriano.mercadolivre.compra.Compra;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class Ranking implements EventoCompraSucesso {

    private final ClienteRankingVendedores clienteRankingVendedores;

    public Ranking(ClienteRankingVendedores clienteRankingVendedores) {
        this.clienteRankingVendedores = clienteRankingVendedores;
    }

    @Override
    public void processar(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(),"Você não pode enviar requisição de ranking de vendedores numa compra não concluída");
        long usuarioId = compra.getUsuario().getId();
        clienteRankingVendedores.enviar(usuarioId, compra.getId());
    }

}