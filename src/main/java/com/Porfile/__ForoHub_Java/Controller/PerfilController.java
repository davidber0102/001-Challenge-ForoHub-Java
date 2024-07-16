package com.Porfile.__ForoHub_Java.Controller;

import com.Porfile.__ForoHub_Java.Domain.Perfil.ActualizarPerfil;
import com.Porfile.__ForoHub_Java.Domain.Perfil.ListarPerfil;
import com.Porfile.__ForoHub_Java.Domain.Perfil.*;
import com.Porfile.__ForoHub_Java.Domain.Perfil.PerfilRepository;
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
@RequestMapping("/perfil")
@SecurityRequirement(name="bearer-key")
public class PerfilController {
    @Autowired
    private PerfilRepository perfilRepository;
    /**************************************
     * REST API GET
     * Obtener todos los Perfiles
     * ENDPOINT :
     * http://localhost:8080/perfil
     ***************************************/
    @GetMapping
    public ResponseEntity<Page<ListarPerfil>> listarUsuarios(@PageableDefault(size = 5) Pageable paged){
        return ResponseEntity.ok(perfilRepository.findByActivoTrue(paged).map(ListarPerfil::new)); }
    /************************************************
     * REST API PUT
     * Actualizar perfil por id
     * ENDPOINT :
     * http://localhost:8080/perfil/1
     *************************************************/
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarPerfil (@RequestBody @Valid ActualizarPerfil actualizarPerf){
        Perfil perfil = perfilRepository.getReferenceById(actualizarPerf.id());
        perfil.ActualizarPerfil(actualizarPerf);
        return ResponseEntity.ok(new ActualizarPerfil(perfil.getId(),perfil.getNombre())); }
    /************************************************
     * REST API DELETE
     * Eliminar un perfil por id
     * ENDPOINT :
     * http://localhost:8080/perfil/1
     *************************************************/
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPerfil(@PathVariable Long id){
        Perfil perfil = perfilRepository.getReferenceById(id);
        perfil.desActivarPerfil();
        return ResponseEntity.noContent().build();    }
    /*******************************************
     * REST API GET
     * Obtener un Usuario pasando el id
     * ENDPOINT :
     * http://localhost:8080/perfil/1
     ********************************************/
    @GetMapping("/{id}")
    public ResponseEntity <RespuestaPerfil> perfilRegistrado(@PathVariable Long id){
        Perfil perfil = perfilRepository.getReferenceById(id);
        var perfilR = new RespuestaPerfil(perfil.getId(), perfil.getNombre());
        return ResponseEntity.ok(perfilR);
    }
}
