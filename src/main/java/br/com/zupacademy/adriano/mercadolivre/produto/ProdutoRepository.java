package br.com.zupacademy.adriano.mercadolivre.produto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    @Query("SELECT produto FROM Produto produto " +
            " INNER JOIN FETCH produto.opnioes opniao " +
            " INNER JOIN FETCH produto.imagens imagem " +
            " INNER JOIN FETCH produto.caracteristicas caracteristica " +
            " INNER JOIN FETCH produto.perguntas perguntas " +
            " WHERE produto.id = :id")
    Optional<Produto> findByIdOtimizada(@Param("id") Long id);

}
