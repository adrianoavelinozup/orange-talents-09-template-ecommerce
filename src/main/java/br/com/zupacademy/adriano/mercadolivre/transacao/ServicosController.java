package br.com.zupacademy.adriano.mercadolivre.transacao;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicos")
public class ServicosController {
    @PostMapping("/nf/{compraId}/{usuarioId}")
    public String nf(@PathVariable Long compraId, @PathVariable Long usuarioId) {
        String msg = "Requsisição para o sistema de NF. Usuário: " + usuarioId + " e Compra: " + compraId;
        System.out.println(msg);
        return msg;
    }

    @PostMapping("/ranking/{vendedorId}/{compraId}")
    public String ranking(@PathVariable Long vendedorId, @PathVariable Long compraId) {
        String msg = "Requsisição para o sistema de Ranking de vendedores.Vendedor: " + vendedorId + ", Compra: " + compraId;
        System.out.println(msg);
        return msg;
    }

}
