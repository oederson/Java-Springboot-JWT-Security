package eder.desenvolve.api.dominio.produto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Produto {
    @Id
    private String id;
    private String titulo;
    private String descricao;
    private String imagem;
    private List<String> categoria;
    private List<String> tamanho;
    private List<String> color;
    private Double preco;

    public Produto(DadosCadastroProduto dados) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.imagem = dados.imagem();
        this.categoria = new ArrayList<>(dados.categoria());
        this.tamanho = new ArrayList<>(dados.tamanho());
        this.color = new ArrayList<>(dados.color());
        this.preco = dados.preco();
    }

    public Produto(String titulo, String descricao, String imagem, List<String> categoria, List<String> tamanho, List<String> color, Double preco) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;
        this.categoria = new ArrayList<>(color);
        this.tamanho = new ArrayList<>(color);
        this.color = new ArrayList<>(color);
        this.preco = preco;
    }

    public void atualizarInfo(DadosAtualizacaoProduto dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.categoria() != null) {
            this.categoria = new ArrayList<>(dados.categoria());
        }
        if (dados.imagem() != null) {
            this.imagem = dados.imagem();
        }
        if (dados.tamanho() != null) {
            this.tamanho = new ArrayList<>(dados.tamanho());
        }
        if (dados.color() != null) {
            this.color = new ArrayList<>(dados.color());
        }
        if (dados.preco() != null) {
            this.preco = dados.preco();
        }
    }
}
