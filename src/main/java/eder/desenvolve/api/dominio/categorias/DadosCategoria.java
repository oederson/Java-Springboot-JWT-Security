package eder.desenvolve.api.dominio.categorias;

import eder.desenvolve.api.dominio.produto.Produto;

public record DadosCategoria(
        String id,
        String nome
)
{
    public DadosCategoria(Categoria categoria) {
    this(
            categoria.getId(),
            categoria.getNome()
    );}
    public  DadosCategoria(String id, String nome){
        this.id = id;
        this.nome = nome;
    }
}
