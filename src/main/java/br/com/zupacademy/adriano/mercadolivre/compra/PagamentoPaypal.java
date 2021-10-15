package br.com.zupacademy.adriano.mercadolivre.compra;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PagamentoPaypal implements GatewayPagamento {
    @Override
    public String criarUrlDeRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
        String urlRetorno = uriComponentsBuilder.path("/retorno-paypal/{id}").buildAndExpand(compra.getId()).toUri().toString();
        return "paypal.com/" + compra.getId() + "?redirectUrl="
                + urlRetorno;
    }
}
