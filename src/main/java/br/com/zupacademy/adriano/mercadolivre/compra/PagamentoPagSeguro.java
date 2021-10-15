package br.com.zupacademy.adriano.mercadolivre.compra;

import org.springframework.web.util.UriComponentsBuilder;

public class PagamentoPagSeguro implements GatewayPagamento {
    @Override
    public String criarUrlDeRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
        String urlRetorno = uriComponentsBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(compra.getId()).toUri().toString();
        return "pagseguro.com/" + compra.getId() + "?redirectUrl="
                + urlRetorno;
    }
}
