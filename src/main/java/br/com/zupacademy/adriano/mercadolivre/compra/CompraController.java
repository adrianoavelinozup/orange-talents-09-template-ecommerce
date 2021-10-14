package br.com.zupacademy.adriano.mercadolivre.compra;

import br.com.zupacademy.adriano.mercadolivre.pergunta.Servicos;
import br.com.zupacademy.adriano.mercadolivre.produto.Produto;
import br.com.zupacademy.adriano.mercadolivre.seguranca.UsuarioLogado;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final EntityManager entityManager;
    private final Servicos servicos;


    public CompraController(EntityManager entityManager, Servicos servicos) {
        this.entityManager = entityManager;
        this.servicos = servicos;
    }

    @PostMapping
    @Transactional
    public String gerar(@RequestBody @Valid CompraRequest compraRequest,
                        @AuthenticationPrincipal UsuarioLogado usuarioLogado,
                        UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Compra compra = compraRequest.toModel(entityManager, usuarioLogado.get());
        Produto produto = compra.getProduto();
        if(produto.temEstoque(compra.getQuantidade())) {
            produto.abateEstoque(compra.getQuantidade());
            entityManager.persist(compra);
            servicos.enviarEmailNovaCompra(produto);
            return compra.gerarUrlDeRetorno(uriComponentsBuilder);
        }

        BindException problemaComEstoque = new BindException(compraRequest, "novaCompraRequest");
        problemaComEstoque.reject("quantidade", null,
                "Não foi possível realizar a compra.  O produto não tem estoque disponível");

        throw problemaComEstoque;
    }

}
