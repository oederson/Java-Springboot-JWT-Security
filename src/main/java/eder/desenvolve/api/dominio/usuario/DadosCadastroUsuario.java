package eder.desenvolve.api.dominio.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public record DadosCadastroUsuario(
        @NotBlank
        String login,
        @NotBlank
        String nomeDeUsuario,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        Boolean tipo) {
}
