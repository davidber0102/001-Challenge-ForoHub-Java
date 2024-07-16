package com.Porfile.__ForoHub_Java.Controller;

import com.Porfile.__ForoHub_Java.Domain.Respuesta.*;
import com.Porfile.__ForoHub_Java.Domain.Topico.TopicoRepository;
import com.Porfile.__ForoHub_Java.Domain.Usuario.UsuarioRepository;
import com.Porfile.__ForoHub_Java.Infra.Errores.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/respuesta")
@SecurityRequirement(name="bearer-key")
public class RespuestaController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ServicioRespuesta respuestaService;
    @Autowired
    private RespuestaRepository repositoryRespuesta;

    /***********************************
     * REST API POST
     * Registrar nueva Respuesta
     * ENDPOINT :
     * http://localhost:8080/respuesta
     *************************************/
    @PostMapping
    @Transactional
    public ResponseEntity respuestaRegistrada (@RequestBody @Valid RegistroRespuesta respuesta)
                throws ValidacionDeIntegridad {
        var respuestaRegistrada = respuestaService.respuestaCreada(respuesta);
        return ResponseEntity.ok(respuestaRegistrada);
    }

    /**************************************
     * REST API GET
     * Listar  Respuestas
     * ENDPOINT :
     * http://localhost:8080/respuesta/respuestas
     ***************************************/
    @GetMapping("/respuestas")
    public ResponseEntity<Page<ListarRespuestas>>  listarRespuestas(@PageableDefault(size = 5) Pageable paged){
        return ResponseEntity.ok(repositoryRespuesta.findByActivoTrue(paged).map(ListarRespuestas::new));
    }

    /************************************************
     * REST API PUT
     * Actualizar respuesta por id
     * ENDPOINT :
     * http://localhost:8080/respuesta/1
     *************************************************/
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity respuestaActualizada(@RequestBody @Valid ActualizacionRespuesta actualizacionRespuesta){
        Respuesta respuesta= repositoryRespuesta.getReferenceById(actualizacionRespuesta.id());
        respuesta.ActualizacionRespuesta(actualizacionRespuesta);
        return ResponseEntity.ok(new RespuestaCreada(
                respuesta.getId(),respuesta.getMensaje(),
                respuesta.getTopico().getId(), respuesta.getFechaCreacion(),
                 respuesta.getUsuario().getId()
                ));
    }

    /************************************************
     * REST API DELETE
     * Eliminar una respuesta por id
     * ENDPOINT :
     * http://localhost:8080/respuesta/1
     *************************************************/
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        Respuesta respuesta = repositoryRespuesta.getReferenceById(id);
        respuesta.desActivarRespuesta();
        return ResponseEntity.noContent().build();
    }
    /*******************************************
     * REST API GET
     * Obtener una Respuesta pasando el id
     * ENDPOINT :
     * http://localhost:8080/respuesta/1
     ********************************************/
    @GetMapping("/{id}")
    public ResponseEntity <RespuestaCreada> respuestaCreada(@PathVariable Long id){
        Respuesta respuesta= repositoryRespuesta.getReferenceById(id);
        var respuestaRegistrada = new RespuestaCreada(
                respuesta.getId(),respuesta.getMensaje(),
                respuesta.getTopico().getId(), respuesta.getFechaCreacion(),
                respuesta.getUsuario().getId());
        return ResponseEntity.ok(respuestaRegistrada);
    }
}
