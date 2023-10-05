package eder.desenvolve.api.dominio.pedido;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Document
public class Pedido {
    @Id
    private String id;
    private String idUsuario;
    private List<String> produtos;
    private LocalDateTime dataCriacao;

    public Pedido(String idUsuario, List<String> produtos, LocalDateTime dataCriacao) {
        this.idUsuario = idUsuario;
        this.produtos = new ArrayList<>(produtos);
        this.dataCriacao = LocalDateTime.now();
    }
    public Pedido (DadosCadastroPedido dados) {
        this.idUsuario = dados.idUsuario();
        this.produtos = new ArrayList<>(dados.produtos());
        this.dataCriacao = LocalDateTime.now();

    }
    public void atualizarInfo(DadosAtualizacaoPedido dados){
        if(dados.idUsuario() != null){
            this.idUsuario = dados.idUsuario();
        }
        if(dados.produtos() != null){
            this.produtos = new ArrayList<>(dados.produtos());
        }

    }
}
