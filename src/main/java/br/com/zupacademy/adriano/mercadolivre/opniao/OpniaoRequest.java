package br.com.zupacademy.adriano.mercadolivre.opniao;

import br.com.zupacademy.adriano.mercadolivre.produto.Produto;
import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;
import br.com.zupacademy.adriano.mercadolivre.validacoes.ExisteId;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpniaoRequest {
    @Min(1)
    @Max(5)
    @NotNull
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500, min = 1)
    private String descricao;

    @NotNull
    @ExisteId(classeDaEntidade = Produto.class, nomeDoCampo = "id")
    private Long produtoId;

    public OpniaoRequest(
                        @Min(1) @Max(5) @NotNull Integer nota,
                        @NotBlank String titulo,
                        @NotBlank @Length(max = 500, min = 1) String descricao,
                        @NotNull Long produtoId) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produtoId = produtoId;
    }

    public Opniao toModel(EntityManager entityManager, @NotNull @Valid Usuario usuario) {
        Produto produto = entityManager.find(Produto.class, produtoId);
        return new Opniao(
                this.nota,
                this.titulo,
                this.descricao,
                produto,
                usuario
        );
    }
}
