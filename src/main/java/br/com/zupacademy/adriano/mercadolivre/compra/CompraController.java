package br.com.zupacademy.adriano.mercadolivre.compra;

import br.com.zupacademy.adriano.mercadolivre.pergunta.DadosDoEmail;
import br.com.zupacademy.adriano.mercadolivre.pergunta.ServicoDeEmail;
import br.com.zupacademy.adriano.mercadolivre.produto.Produto;
import br.com.zupacademy.adriano.mercadolivre.seguranca.UsuarioLogado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final EntityManager entityManager;
    private final ServicoDeEmail servicoDeEmail;


    public CompraController(EntityManager entityManager, ServicoDeEmail servicoDeEmail) {
        this.entityManager = entityManager;
        this.servicoDeEmail = servicoDeEmail;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> gerar(@RequestBody @Valid CompraRequest compraRequest, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Compra compra = compraRequest.toModel(entityManager, usuarioLogado.get());
        Produto produto = compra.getProduto();
        if(produto.temEstoque(compra.getQuantidade())) {
            produto.abateEstoque(compra.getQuantidade());
            entityManager.persist(compra);
            servicoDeEmail.enviarEmailNovaCompra(produto);
            String urlPagamento = compra.getFormaPagamento().getGatewayPagamento().encaminharPagamento(compra);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlPagamento)).build();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "o produto não tem estoque disponível!");
    }

}
