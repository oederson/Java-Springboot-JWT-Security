package eder.desenvolve.api.dominio.produto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record DadosCadastroProduto(
    String titulo,
    String descricao,
    String imagem,
    List<String> categoria,
    List<String> tamanho,
    List<String> color,
    Double preco) {
    public DadosCadastroProduto imagem(String nomeImagem) {
        return new DadosCadastroProduto(
                this.titulo(),
                this.descricao(),
                nomeImagem,
                this.categoria(),
                this.tamanho(),
                this.color(),
                this.preco()
        );
    }

}