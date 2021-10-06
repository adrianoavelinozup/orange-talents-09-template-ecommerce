package br.com.zupacademy.adriano.mercadolivre.categoria;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final EntityManager entityManager;

    public CategoriaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CategoriaRequest categoriaRequest) {
        Categoria categoria = categoriaRequest.toModel(entityManager);
        entityManager.persist(categoria);
    }
}
