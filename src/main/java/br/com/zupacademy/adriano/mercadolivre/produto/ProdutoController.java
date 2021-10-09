package br.com.zupacademy.adriano.mercadolivre.produto;

import br.com.zupacademy.adriano.mercadolivre.seguranca.UsuarioLogado;
import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;
import br.com.zupacademy.adriano.mercadolivre.validacao.ErroDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final EntityManager entityManager;
    private final Uploader uploader;

    public ProdutoController(EntityManager entityManager, Uploader uploader) {
        this.entityManager = entityManager;
        this.uploader = uploader;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal UsuarioLogado dono) {
        Produto produto = produtoRequest.toModel(entityManager, dono.get());
        entityManager.persist(produto);
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionarImagensNoProduto(@PathVariable Long id,
        @Valid ImagensProdutoRequest imagemRequest, @AuthenticationPrincipal UsuarioLogado userDetails) throws Exception {
        Produto produto = entityManager.find(Produto.class, id);
        Usuario dono  = userDetails.getUsuario();

        if (produto == null) {
            ErroDto erroDto = new ErroDto("produto", "não encontrado");
            ErroDto erro = new ErroDto("id", "Produto não encontrado");
            return new ResponseEntity<ErroDto>(erro, HttpStatus.NOT_FOUND);
        }

        if (!produto.getDono().equals(dono)) {
            ErroDto erro = new ErroDto("erro", "Não tem permissão para adiconar fotos neste produto");
            return new ResponseEntity<ErroDto>(erro, HttpStatus.FORBIDDEN);
        }

        Set<String> imagensUpadas = uploader.enviar(imagemRequest.getImagens());
        produto.adicionarImagens(imagensUpadas);
        entityManager.merge(produto);

        return ResponseEntity.ok().build();
    }

}
