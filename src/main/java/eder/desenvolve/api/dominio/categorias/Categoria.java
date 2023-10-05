package eder.desenvolve.api.dominio.categorias;

import eder.desenvolve.api.dominio.produto.DadosAtualizacaoProduto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Categoria {
    @Id
    private String id;
    private String nome;

    public Categoria(String id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public Categoria(DadosCategoria categoria) {
        this.id = categoria.id();
        this.nome = categoria.nome();
    }
    public void atualizarInfo (DadosAtualizacaoCategoria categoria) {
        if (categoria.nome() != null) {
            this.nome = categoria.nome();
        }


    }
}
