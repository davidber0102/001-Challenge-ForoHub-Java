package com.Porfile.__ForoHub_Java.Domain.Usuario;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByNombre(String username);

    Page<Usuario> findByActivoTrue(Pageable paged);

    boolean existsByEmail(String email);

    boolean existsByNombre(String nombre);
}
