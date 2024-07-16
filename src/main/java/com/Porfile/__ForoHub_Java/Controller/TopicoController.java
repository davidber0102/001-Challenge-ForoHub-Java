package com.Porfile.__ForoHub_Java.Controller;

import com.Porfile.__ForoHub_Java.Domain.Topico.*;
import com.Porfile.__ForoHub_Java.Domain.Usuario.UsuarioRepository;
import com.Porfile.__ForoHub_Java.Infra.Errores.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/topico")
@SecurityRequirement(name="bearer-key")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ServicioTopico servicioTopico;

    /***********************************
     * REST API POST
     * Registrar nuevo Topico
     * ENDPOINT :
     * http://localhost:8081/topico
     *************************************/
    @PostMapping("/topico")
    @Transactional
    @Operation(summary = "Registra un nuevo topico en la base de datos")
    public ResponseEntity topicoRegistrado(@RequestBody @Valid RegistroTopico registroTopico) throws ValidacionDeIntegridad {
            var topicoRegistrado = servicioTopico.topicoCreado(registroTopico);
            return ResponseEntity.ok(topicoRegistrado);
    }

    /**************************************
     * REST API GET
     * Listar  Topicos
     * ENDPOINT :
     * http://localhost:8081/topico/topicos
     ***************************************/
    @GetMapping
    @Operation(summary = "Obtiene el listado de Topicos")
    public ResponseEntity<Page<ListarTopicos>> listarTopicos (@PageableDefault(size = 5) Pageable paged){
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paged).map(ListarTopicos::new));
    }

    /************************************************
     * REST API PUT
     * Actualizar topico por id
     * ENDPOINT :
     * http://localhost:8080/topico/1
     *************************************************/
    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualiza los datos de un topico existente")
    public ResponseEntity topicoActualizado(@RequestBody @Valid ActualizarTopico actualizar){
        Topico topico = topicoRepository.getReferenceById(actualizar.id());
        topico.actualizarTopico(actualizar);
        return ResponseEntity.ok(new RespuestaTopico(
                topico.getId(),
                topico.getTitulo(), topico.getMensaje(),
                topico.getUsuario().getId(),
                topico.getCurso().getNombre(), topico.getFechaCreacion()        ));
    }

    /************************************************
     * REST API DELETE
     * Eliminar topico por id
     * ENDPOINT :
     * http://localhost:8080/topico/1
     *************************************************/
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico= topicoRepository.getReferenceById(id);
        topico.desActivarTopico();
        return ResponseEntity.noContent().build();
    }
    /*******************************************
     * REST API GET
     * Obtener  Topico  id
     * ENDPOINT :
     * http://localhost:8080/topico/1
     ********************************************/
    @GetMapping("/{id}")
    public ResponseEntity <RespuestaTopico> respuestaTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var topicoId = new RespuestaTopico(
                topico.getId(),
                topico.getTitulo(), topico.getMensaje(),
                topico.getUsuario().getId(),
                topico.getCurso().getNombre(), topico.getFechaCreacion() );
        return ResponseEntity.ok(topicoId);
    }
}
