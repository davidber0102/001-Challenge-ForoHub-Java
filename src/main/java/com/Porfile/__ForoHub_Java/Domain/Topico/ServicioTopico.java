package com.Porfile.__ForoHub_Java.Domain.Topico;

import com.Porfile.__ForoHub_Java.Domain.Usuario.UsuarioRepository;
import com.Porfile.__ForoHub_Java.Infra.Errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioTopico {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public RespuestaTopico topicoCreado(RegistroTopico registroTopico) {
        if (!usuarioRepository.findById(registroTopico.usuario_Id()).isPresent()) {
            throw new ValidacionDeIntegridad("ID de usuario ingresado no está registrado en la base de datos.");
        }

        var titulo = registroTopico.titulo();
        if (titulo != null && topicoRepository.existsByTitleIgnoreCase(titulo)) {
            throw new ValidacionDeIntegridad("El Topicoque ha escrito ya está presente en la base de datos. Por favor verifique.");
        }

        String mensaje = registroTopico.mensaje();
        if (mensaje != null && topicoRepository.existsByMensajeIgnoreCase(mensaje)){
            throw new ValidacionDeIntegridad("Mensaje escrito está presente en la base de datos. Por favor verifique.");
        }

        var usuario =usuarioRepository.findById(registroTopico.usuario_Id()).get();
        var topicoId = new Topico(null, titulo, mensaje, registroTopico.fechaCreacion(),
                 usuario, registroTopico.curso());
        topicoRepository.save(topicoId);
        return new RespuestaTopico(topicoId);
    }
}
