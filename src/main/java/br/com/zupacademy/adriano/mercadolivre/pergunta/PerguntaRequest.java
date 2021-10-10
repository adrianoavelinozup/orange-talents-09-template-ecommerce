package br.com.zupacademy.adriano.mercadolivre.pergunta;

import br.com.zupacademy.adriano.mercadolivre.produto.Produto;
import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;
import br.com.zupacademy.adriano.mercadolivre.validacoes.ExisteId;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerguntaRequest {
    @NotBlank
    private String titulo;

    @NotNull
    @ExisteId(classeDaEntidade = Usuario.class, nomeDoCampo = "id")
    private Long usuarioId;

    @NotNull
    @ExisteId(classeDaEntidade = Produto.class, nomeDoCampo = "id")
    private Long produtoId;


    public PerguntaRequest(@NotBlank String titulo,
                           @NotBlank Long usuarioId,
                           @NotBlank Long produtoId) {
        Assert.isTrue(StringUtils.hasLength(titulo), "Título não pode estar em branco");
        Assert.notNull(usuarioId, "Id do usuário não pode ser nulo");
        Assert.notNull(produtoId, "Id do produto não pode ser nulo");
        this.titulo = titulo;
        this.usuarioId = usuarioId;
        this.produtoId = produtoId;
    }

    public Pergunta toModel(EntityManager entityManager) {
        Produto produto = entityManager.find(Produto.class, produtoId);
        Usuario usuario = entityManager.find(Usuario.class, produtoId);

        Assert.isTrue(StringUtils.hasLength(this.titulo), "Título não pode estar em branco");
        Assert.notNull(usuario, "Usuário não pode estar nulo");
        Assert.notNull(produto, "Produto não pode estar nulo");

        return new Pergunta(this.titulo, usuario, produto);
    }
}
