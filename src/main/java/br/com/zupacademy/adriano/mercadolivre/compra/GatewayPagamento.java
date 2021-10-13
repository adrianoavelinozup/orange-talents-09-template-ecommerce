package br.com.zupacademy.adriano.mercadolivre.compra;

import org.springframework.web.util.UriComponentsBuilder;

public interface GatewayPagamento {
    String criarUrlDeRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder);
}
