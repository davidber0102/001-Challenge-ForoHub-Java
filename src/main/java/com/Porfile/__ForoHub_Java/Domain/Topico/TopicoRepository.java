package com.Porfile.__ForoHub_Java.Domain.Topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTitleIgnoreCase(String title);
    boolean existsByMensajeIgnoreCase(String mensaje);
    Page<Topico> findByActivoTrue(Pageable paged);
}
