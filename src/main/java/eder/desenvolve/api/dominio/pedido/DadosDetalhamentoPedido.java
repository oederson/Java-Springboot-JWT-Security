package eder.desenvolve.api.dominio.pedido;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoPedido(
        String id,
        String idUsuario,
        List<String> produtos,
        LocalDateTime dataCriacao) {
}
