package com.Porfile.__ForoHub_Java.Domain.Perfil;

import com.Porfile.__ForoHub_Java.Infra.Errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPerfil {
    @Autowired
    private PerfilRepository repository;

    public RespuestaPerfil perfilCreado(RegistroPerfil registroPerfil) {
        var nombreP = registroPerfil.nombre();
        if (nombreP != null && repository.existsByNombreIgnoreCase(nombreP)) {
            throw new ValidacionDeIntegridad("Nombre de Curso ya está presente en la base de datos. Por favor revise el tópico existente.");
        }
        var perfilP = new Perfil(null, nombreP);
        repository.save(perfilP);
        return new RespuestaPerfil(perfilP);
    }

    }


