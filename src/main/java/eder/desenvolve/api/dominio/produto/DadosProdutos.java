package eder.desenvolve.api.dominio.produto;


import java.util.List;

public record DadosProdutos(
        String id,
        String titulo,
        String descricao,
        String imagem,
        List<String> categoria,
        List<String> tamanho,
        List<String> color,
        Double preco) {

    public DadosProdutos(Produto produto) {
        this(
                produto.getId(),
                produto.getTitulo(),
                produto.getDescricao(),
                produto.getImagem(),
                produto.getCategoria(),
                produto.getTamanho(),
                produto.getColor(),
                produto.getPreco()
        );
    }

    public DadosProdutos(String id, String titulo, String descricao, String imagem, List<String> categoria, List<String> tamanho, List<String> color, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;
        this.categoria = categoria;
        this.tamanho = tamanho;
        this.color = color;
        this.preco = preco;
    }
}