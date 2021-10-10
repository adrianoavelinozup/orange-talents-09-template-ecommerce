package br.com.zupacademy.adriano.mercadolivre.opniao;

import br.com.zupacademy.adriano.mercadolivre.seguranca.UsuarioLogado;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/opnioes")
public class OpniaoController {
    private final EntityManager entityManager;

    public OpniaoController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid OpniaoRequest opniaoRequest, @AuthenticationPrincipal UsuarioLogado usuario) {
        Opniao opniao = opniaoRequest.toModel(entityManager, usuario.getUsuario());
        entityManager.persist(opniao);
    }
}
