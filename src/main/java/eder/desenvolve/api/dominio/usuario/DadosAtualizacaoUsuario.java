package eder.desenvolve.api.dominio.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(
        String login,
        @NotNull
        String id,
        String nomeDeUsuario,
        String email,
        String senha,
        Boolean tipo) {
}
