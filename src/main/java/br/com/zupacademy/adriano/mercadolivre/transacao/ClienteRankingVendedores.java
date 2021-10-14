package br.com.zupacademy.adriano.mercadolivre.transacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ranking", url = "localhost:8080/servicos/ranking")
public interface ClienteRankingVendedores {
    @RequestMapping(method = RequestMethod.POST, value = "/{vendedorId}/{compraId}")
    void enviar(@PathVariable Long vendedorId, @PathVariable Long compraId, @RequestHeader("Authorization") String token);
}
