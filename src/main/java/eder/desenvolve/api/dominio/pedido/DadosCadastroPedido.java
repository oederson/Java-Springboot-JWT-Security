package eder.desenvolve.api.dominio.pedido;

import java.time.LocalDateTime;
import java.util.List;

public record DadosCadastroPedido(
        String id,
        String idUsuario,
        List<String> produtos,
        LocalDateTime dataCriacao
) {
}
