package br.com.zupacademy.adriano.mercadolivre.pergunta;

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
    private final ServicoDeEmail servicoDeEmail;

    public PerguntaController(EntityManager entityManager, ServicoDeEmail servicoDeEmail) {
        this.entityManager = entityManager;
        this.servicoDeEmail = servicoDeEmail;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PerguntaRequest perguntaRequest) {
        Pergunta pergunta = perguntaRequest.toModel(entityManager);
        entityManager.persist(pergunta);
        servicoDeEmail.enviar(pergunta);
    }
}
