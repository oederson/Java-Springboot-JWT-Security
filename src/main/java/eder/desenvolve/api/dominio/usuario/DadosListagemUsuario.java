package eder.desenvolve.api.dominio.usuario;


import java.time.LocalDateTime;

public record DadosListagemUsuario(
        String login,
        String id,
        String nomeDeUsuario,
        String email,
        String senha,
        Boolean tipo,
        LocalDateTime dataCriacao) {
    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getLogin(), usuario.getId(), usuario.getNomeDeUsuario(), usuario.getEmail(), usuario.getSenha(), usuario.getTipo(), usuario.getDataCriacao());
    }
}
