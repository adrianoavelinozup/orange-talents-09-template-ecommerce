package br.com.zupacademy.adriano.mercadolivre.transacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "nf", url = "http://localhost:8080/servicos/nf")
public interface ClienteNF {
    @RequestMapping(method = RequestMethod.POST, value = "/{compraId}/{usuarioId}")
    void enviar(@PathVariable Long usuarioId, @PathVariable Long compraId);
}
