package com.Porfile.__ForoHub_Java.Domain.Perfil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    boolean existsByNombreIgnoreCase(String nombreP);

    Page<Perfil> findByActivoTrue(Pageable paged);
}
