package br.com.zupacademy.adriano.mercadolivre.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoDetalhadoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoDetalhadoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        Optional<Produto> possivelProduto = produtoRepository.findByIdOtimizada(id);
        if (possivelProduto.isPresent()) {
            ProdutoDetalhadoResponse produtoDetalhadoResponse = new ProdutoDetalhadoResponse(possivelProduto.get());
            return ResponseEntity.ok(produtoDetalhadoResponse);
        }

        return ResponseEntity.notFound().build();
    }
}
