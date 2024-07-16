CREATE TABLE respuestas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje VARCHAR(255) NOT NULL,
    topico_id BIGINT,
    fecha_creacion DATETIME NOT NULL,
    usuario_id BIGINT,
    solucion BOOLEAN NOT NULL,
    activo BOOLEAN NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (topico_id) REFERENCES topicos(id)
);
