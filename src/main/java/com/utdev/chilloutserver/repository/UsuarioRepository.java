package com.utdev.chilloutserver.repository;

import com.utdev.chilloutserver.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Usuario findFirstByUsernameIgnoreCase(String username);

}
