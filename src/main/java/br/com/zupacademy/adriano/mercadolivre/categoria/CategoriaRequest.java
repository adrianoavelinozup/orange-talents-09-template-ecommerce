package br.com.zupacademy.adriano.mercadolivre.categoria;

import br.com.zupacademy.adriano.mercadolivre.validacoes.ExisteId;
import br.com.zupacademy.adriano.mercadolivre.validacoes.ValorUnico;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class CategoriaRequest {
    @NotBlank
    @ValorUnico(classeDaEntidade = Categoria.class, nomeDoCampo = "nome")
    private String nome;

    @ExisteId(classeDaEntidade = Categoria.class, nomeDoCampo = "id")
    private Long categoriaMaeId;

    public CategoriaRequest(String nome, Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }

    public Categoria toModel(EntityManager entityManager) {
        Categoria categoria = new Categoria(this.nome);
        if (categoriaMaeId != null) {
            Categoria categoriaMae = entityManager.find(Categoria.class, categoriaMaeId);
            categoria.setCategoria(categoriaMae);
        }
        return categoria;
    }
}
