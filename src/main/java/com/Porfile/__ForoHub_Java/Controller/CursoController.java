package com.Porfile.__ForoHub_Java.Controller;

import com.Porfile.__ForoHub_Java.Domain.Curso.*;
import com.Porfile.__ForoHub_Java.Infra.Errores.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name="bearer-key")
public class CursoController {
    private CursoRepository cursoRepository;
    /***********************************
     * REST API POST
     * Registrar nuevo Curso
     * ENDPOINT :
     * http://localhost:8080/cursos/curso
     *************************************/
    @PostMapping("/curso")
    @Transactional
    @Operation(summary = "Registra un nuevo curso en la base de datos")
    public ResponseEntity cursoRegistrado(@RequestBody @Valid RegistroCurso registroCurso, UriComponentsBuilder uriComponentsBuilder) throws ValidacionDeIntegridad {
        var curso = cursoRepository.save(new Curso(registroCurso));
        RespuestaCurso respuestaCurso = new RespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria());
        URI url = uriComponentsBuilder.path("cursos/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaCurso);
    }
    /**************************************
     * REST API GET
     * Lista todos los cursos
     * ENDPOINT :
     *  http://localhost:8080/cursos/curso
     ***************************************/
    @GetMapping
    @Operation(summary = "Obtiene el listado de Cursos")
    public ResponseEntity<Page<ListadoCursos>>  listadoMedicos(@PageableDefault(size =5) Pageable paginacion){
        return ResponseEntity.ok(cursoRepository.findByActivoTrue(paginacion).map(ListadoCursos::new));
    }
    /************************************************
     * REST API PUT
     * Actualizar un curso por id
     * ENDPOINT :
     *  http://localhost:8080/cursos/curso
     *************************************************/
    @PutMapping("/curso/{id}")
    @Transactional
    public ResponseEntity cursoActualizado (@RequestBody @Valid ActualizarCurso actualizarCurso){
        Curso curso = cursoRepository.getReferenceById(actualizarCurso.id());
        curso.cursoActualizado(actualizarCurso);
        return ResponseEntity.ok(new RespuestaCurso(
                curso.getId(), curso.getNombre(), curso.getCategoria()
        ));
    }

    /************************************************
     * REST API DELETE
     * Eliminar un curso por id
     * ENDPOINT :
     *  http://localhost:8080/cursos/curso
     *************************************************/
    @DeleteMapping("/curso/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Curso curso= cursoRepository.getReferenceById(id);
       curso.desActivarCurso();
        return ResponseEntity.noContent().build();
    }

    /*******************************************
     * REST API GET
     * Obtener un curso pasando el id
     * ENDPOINT :
     *  http://localhost:8080/cursos/curso
     ********************************************/
    @GetMapping("/curso/{id}")
    public ResponseEntity <RespuestaCurso> respuestaTopico(@PathVariable Long id){
        Curso curso= cursoRepository.getReferenceById(id);
        var cursoId = new RespuestaCurso(
                curso.getId(), curso.getNombre(), curso.getCategoria());
        return ResponseEntity.ok(cursoId);
    }


}
