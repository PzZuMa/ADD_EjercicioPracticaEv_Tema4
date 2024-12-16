package org.example.modelos;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Usuario implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String correo;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Comentario> comentarios = new ArrayList<>();

    public void addComentario(Comentario c) {
        c.setUser(this);
        comentarios.add(c);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", comentarios=" + comentarios +
                '}';
    }
}
