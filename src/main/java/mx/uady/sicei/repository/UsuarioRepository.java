package mx.uady.sicei.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.uady.sicei.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsuario(String usuario);

    Optional<Usuario> findByUsuarioAndPassword(String usuario, String password);

    Optional<Usuario> findByEmailAndPassword(String email, String password);

    Usuario findBySecret(String secret);

}