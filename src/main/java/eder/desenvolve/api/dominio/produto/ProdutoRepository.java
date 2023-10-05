package eder.desenvolve.api.dominio.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
    Page<Produto> findByCategoria(String categoria, Pageable pageable);

}
