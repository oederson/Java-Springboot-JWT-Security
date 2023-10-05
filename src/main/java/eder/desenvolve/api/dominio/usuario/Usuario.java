package eder.desenvolve.api.dominio.usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@Document
public class Usuario implements UserDetails {
    @Id
    private String id;
    private String login;
    private String nomeDeUsuario;
    private String email;
    private String senha;
    private Boolean tipo;
    private LocalDateTime dataCriacao;

    public Usuario(String login, String nomeDeUsuario, String email, String senha, Boolean tipo,LocalDateTime dataCriacao) {
        this.login = login;
        this.nomeDeUsuario = nomeDeUsuario;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.dataCriacao = LocalDateTime.now();
    }

    public Usuario(DadosCadastroUsuario dados) {
        this.login = dados.login();
        this.nomeDeUsuario = dados.nomeDeUsuario();
        this.email = dados.email();
        this.senha = dados.senha();
        this.tipo = dados.tipo();
        this.dataCriacao = LocalDateTime.now();
    }
    public void atualizarInfo(DadosAtualizacaoUsuario dados){
        if (dados.login() != null){
            this.login = dados.login();
        }
        if(dados.nomeDeUsuario() != null){
            this.nomeDeUsuario = dados.nomeDeUsuario();
        }
        if(dados.email() != null){
            this.email = dados.email();
        }
        if(dados.senha() != null){
            this.senha = dados.senha();
        }
        if(dados.tipo() != null){
            this.tipo = dados.tipo();
        }
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
