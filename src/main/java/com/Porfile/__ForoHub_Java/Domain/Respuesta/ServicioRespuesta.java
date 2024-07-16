package com.Porfile.__ForoHub_Java.Domain.Respuesta;

import com.Porfile.__ForoHub_Java.Domain.Topico.TopicoRepository;
import com.Porfile.__ForoHub_Java.Domain.Usuario.UsuarioRepository;
import com.Porfile.__ForoHub_Java.Infra.Errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioRespuesta {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;

    public RespuestaCreada respuestaCreada(RegistroRespuesta registroRespuesta){
        if(!usuarioRepository.findById(registroRespuesta.usuario_id()).isPresent()){
            throw new ValidacionDeIntegridad("ID de usuario sin registro en la base de datos. ");
        }

        if (!topicoRepository.findById(registroRespuesta.topico_id()).isPresent()){
            throw new ValidacionDeIntegridad("Id de tópico sin registro en la base de datos. ");
        }

        var usuario = usuarioRepository.findById(registroRespuesta.usuario_id()).get();
        var topico = topicoRepository.findById(registroRespuesta.topico_id()).get();

        var resuestaVerificada = new Respuesta(null,
                                            registroRespuesta.mensaje(),
                                            topico,
                                                registroRespuesta.fechaCreacion(),
                                            usuario       );
        respuestaRepository.save(resuestaVerificada);
        return new RespuestaCreada(resuestaVerificada);
    }
}
