package br.com.zupacademy.adriano.mercadolivre.categoria;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaRequestTest {
    @Test
    @DisplayName("deve cadastrar categoria sem categoria mãe")
    void deveCadastrarCategoriaASemCategoriaMae() {
        CategoriaRequest request = new CategoriaRequest("cat1", null);

        EntityManager entityManager = Mockito.mock(EntityManager.class);
        request.toModel(entityManager);

        Mockito.verify(entityManager, Mockito.never())
                .find(Mockito.eq(Categoria.class), Mockito.anyLong());
    }

    @Test
    @DisplayName("deve cadastrar categoria com categoria mãe")
    void deveCadastrarCategoriaAComCategoriaMae() {
        Long categoriaMaeId = 1l;
        CategoriaRequest request = new CategoriaRequest("cat1", categoriaMaeId);
        Categoria categoriaMae = new Categoria("categoria mãe");

        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Mockito.when(entityManager.find(Categoria.class, categoriaMaeId))
                        .thenReturn(categoriaMae);

        request.toModel(entityManager);

        Mockito.verify(entityManager).find(Categoria.class, categoriaMaeId);
    }
}