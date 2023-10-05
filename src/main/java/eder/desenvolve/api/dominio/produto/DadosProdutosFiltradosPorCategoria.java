package eder.desenvolve.api.dominio.produto;

import java.util.List;

public record DadosProdutosFiltradosPorCategoria(
     String id,
     String titulo,
     String descricao,
     List<String> categoria,
     List<String> tamanho,
     List<String> color,
     Double preco,

     String imagem
) {
    public DadosProdutosFiltradosPorCategoria(Produto produto) {
        this(
                produto.getId(),
                produto.getTitulo(),
                produto.getDescricao(),
                produto.getCategoria(),
                produto.getTamanho(),
                produto.getColor(),
                produto.getPreco(),
                produto.getImagem()
        );
    }

    public DadosProdutosFiltradosPorCategoria(String id, String titulo, String descricao, List<String> categoria, List<String> tamanho, List<String> color, Double preco,  String imagem) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.tamanho = tamanho;
        this.color = color;
        this.preco = preco;
        this.imagem = imagem;
    }

}
