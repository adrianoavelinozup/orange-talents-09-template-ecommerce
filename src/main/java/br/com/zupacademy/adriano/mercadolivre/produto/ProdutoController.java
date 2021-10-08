package br.com.zupacademy.adriano.mercadolivre.produto;

import br.com.zupacademy.adriano.mercadolivre.seguranca.UsuarioRepository;
import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final EntityManager entityManager;
    private final UsuarioRepository usuarioRepository;

    public ProdutoController(EntityManager entityManager, UsuarioRepository usuarioRepository) {
        this.entityManager = entityManager;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Transactional
    public String cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> dono = usuarioRepository.findByEmail(email);

        Produto produto = produtoRequest.toModel(entityManager, dono.get());
        entityManager.persist(produto);
        return produtoRequest.toString();
    }
}
