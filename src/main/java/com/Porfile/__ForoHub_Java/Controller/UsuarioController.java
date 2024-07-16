package com.Porfile.__ForoHub_Java.Controller;

import com.Porfile.__ForoHub_Java.Domain.Usuario.*;
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
@RequestMapping("/usuario")
@SecurityRequirement(name="bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**************************************
     * REST API GET
     * Obtener todos los Usuarios
     * ENDPOINT :
     * http://localhost:8080/usuario/usuarios
     ***************************************/
    @GetMapping("/usuarios")
    public ResponseEntity<Page<ListarUsuarios>> listarUsuarios(@PageableDefault(size = 5) Pageable paged){
        return ResponseEntity.ok(usuarioRepository.findByActivoTrue(paged).map(ListarUsuarios::new));
    }
    /************************************************
     * REST API PUT
     * Actualizar un usuario por id
     * ENDPOINT :
     * http://localhost:8080/usuario/1
     *************************************************/
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizacionUsuario (@RequestBody @Valid ActualizarUsuario actualizarUsuario){
        Usuario usuario = usuarioRepository.getReferenceById(actualizarUsuario.id());
        usuario.ActualizarUsuario(actualizarUsuario);
        return ResponseEntity.ok(new ActualizarUsuario(usuario.getId(),usuario.getNombre(), usuario.getEmail()));
    }

    /************************************************
     * REST API DELETE
     * Eliminar un usuario por id
     * ENDPOINT :
     * http://localhost:8080/usuario/1
     *************************************************/
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.desActivarUsuario();
        return ResponseEntity.noContent().build();
    }
    /*******************************************
     * REST API GET
     * Obtener un Usuario pasando el id
     * ENDPOINT :
     * http://localhost:8080/usuario/1
     ********************************************/
    @GetMapping("/{id}")
    public ResponseEntity <RespuestaUsuario> usuarioRegistrado(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var usuarioDetail = new RespuestaUsuario(usuario.getId(), usuario.getNombre());
        return ResponseEntity.ok(usuarioDetail);
    }

}
