package eder.desenvolve.api.dominio.usuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository <Usuario, String> {
    UserDetails findByLogin(String login);


}
