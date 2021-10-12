package br.com.zupacademy.adriano.mercadolivre.compra;

import org.springframework.beans.factory.annotation.Value;

public class PagamentoPagSeguro implements GatewayPagamento {

    private String urlPagamento = "pagseguro.com?returnId=%d&redirectUrl=%s";

    private String urlRedirecionamento = "localhost:8080/compras/%d";

    @Override
    public String encaminharPagamento(Compra compra) {
        return String.format(urlPagamento, compra.getId(), String.format(urlRedirecionamento, compra.getId()));
    }
}
