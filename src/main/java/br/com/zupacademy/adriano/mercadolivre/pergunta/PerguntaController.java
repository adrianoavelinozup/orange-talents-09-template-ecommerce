package br.com.zupacademy.adriano.mercadolivre.pergunta;

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
@RequestMapping("/perguntas")
public class PerguntaController {

    private final EntityManager entityManager;
    private final Servicos servicos;

    public PerguntaController(EntityManager entityManager, Servicos servicos) {
        this.entityManager = entityManager;
        this.servicos = servicos;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PerguntaRequest perguntaRequest,
                          @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Pergunta pergunta = perguntaRequest.toModel(entityManager, usuarioLogado.getUsuario());
        entityManager.persist(pergunta);
        servicos.enviarEmailNovaPergunta(pergunta);
    }
}
