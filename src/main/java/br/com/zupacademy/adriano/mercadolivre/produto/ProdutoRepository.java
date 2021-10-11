package br.com.zupacademy.adriano.mercadolivre.produto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    @Query("SELECT produto FROM Produto produto " +
            " INNER JOIN FETCH produto.dono dono " +
            " INNER JOIN FETCH produto.opnioes opniao " +
            " INNER JOIN FETCH produto.imagens imagem " +
            " INNER JOIN FETCH produto.caracteristicas caracteristica " +
            " INNER JOIN FETCH opniao.usuario usuarioOpniao " +
            " WHERE produto.id = :id")
    Optional<Produto> findByIdOtimizada(@Param("id") Long id);

    @Query("SELECT AVG(opniao.nota) FROM Produto produto " +
            " INNER JOIN produto.opnioes opniao " +
            " WHERE produto.id = :id group by produto.id ")
    Long findMediaNotaPorId4(@Param("id") Long id);

    @Query("SELECT COUNT(produto.id) FROM Produto produto " +
            " INNER JOIN produto.opnioes opniao " +
            " WHERE produto.id = :id group by produto.id ")
    Long findQuantidadeTotaLDeNotas(@Param("id") Long id);

}
