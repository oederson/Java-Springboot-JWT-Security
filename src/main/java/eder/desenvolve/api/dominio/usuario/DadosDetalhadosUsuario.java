package eder.desenvolve.api.dominio.usuario;

import java.time.LocalDateTime;

public record DadosDetalhadosUsuario(
        String login,
        String id,
        String nomeDeUsuario,
        String email,
        String senha,
        Boolean tipo

        ) {
    public DadosDetalhadosUsuario (Usuario usuario) {
        this(usuario.getLogin(), usuario.getId(), usuario.getNomeDeUsuario(), usuario.getEmail(), usuario.getSenha(), usuario.getTipo());
    }
}
