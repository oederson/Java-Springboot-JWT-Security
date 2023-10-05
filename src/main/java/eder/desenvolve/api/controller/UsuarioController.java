package eder.desenvolve.api.controller;

import eder.desenvolve.api.dominio.usuario.DadosListagemUsuario;
import eder.desenvolve.api.dominio.usuario.Usuario;
import eder.desenvolve.api.dominio.usuario.UsuarioRepository;
import eder.desenvolve.api.dominio.usuario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder){
        String senhaCriptografada = passwordEncoder.encode(dados.senha());
        Usuario usuario = new Usuario(dados);
        usuario.setSenha(senhaCriptografada);
        usuarioRepository.save(usuario);
        var uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhadosUsuario(usuario));
    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listar(Pageable paginacao){
        var page = usuarioRepository.findAll(paginacao).map(DadosListagemUsuario::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados ){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(dados.id());
        Usuario usuario = usuarioOptional.get();
        usuario.atualizarInfo(dados);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(new DadosDetalhadosUsuario(usuario));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable String id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity porId(@PathVariable String id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        Usuario usuario1 = usuario.get();
        return ResponseEntity.ok(new DadosDetalhadosUsuario(usuario1));
    }
    @GetMapping("/login/{name}")
    @Transactional
    public ResponseEntity procuraPorLogin(@PathVariable String name){
        var usuario = usuarioRepository.findByLogin(name);
        System.out.println(usuario);
        if (usuario != null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
