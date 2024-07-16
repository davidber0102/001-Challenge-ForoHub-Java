package com.Porfile.__ForoHub_Java.Domain.Usuario;

import com.Porfile.__ForoHub_Java.Domain.Perfil.Perfil;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(unique = true, nullable = false)
    @Email
    private String email;
    @Column(nullable = false)
    private String contrasenia;
    private boolean activo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    public Usuario(RegistroUsuario registtroUsuario, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.nombre = registtroUsuario.nombre();
        this.email = registtroUsuario.email();
        this.contrasenia = registtroUsuario.contrasenia();
        this.activo = true;    }

    public void ActualizarUsuario(ActualizarUsuario actualizarUsuario){
        if (actualizarUsuario.nombre() != null){
            this.nombre = actualizarUsuario.nombre();
        }
        if (actualizarUsuario.email() != null) {
            this.email = actualizarUsuario.email();
        }
    }


    public void desActivarUsuario() {
        this.activo = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));   }

    @Override
    public String getPassword() {return contrasenia;    }

    @Override
    public String getUsername() {return nombre;}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return activo;}
}
