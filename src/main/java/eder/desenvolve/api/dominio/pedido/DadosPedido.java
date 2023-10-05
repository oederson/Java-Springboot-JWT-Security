package eder.desenvolve.api.dominio.pedido;

import java.time.LocalDateTime;
import java.util.List;

public record DadosPedido(
        String id,
        String idUsuario,
        List<String>produtos,
        LocalDateTime dataCriacao
) {
    public DadosPedido (Pedido pedido){
        this(pedido.getId(), pedido.getIdUsuario(), pedido.getProdutos(), pedido.getDataCriacao());

    }
}
