package eder.desenvolve.api.dominio.produto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record DadosAtualizacaoProduto(
        String id,
        String titulo,
        String descricao,
        String imagem,
        List<String> categoria,
        List<String> tamanho,
        List<String> color,
        Double preco
) {

}
