package org.example.modelos;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Comentario implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String texto;
    private Double valoracion;

    @ManyToOne
    private Usuario user;

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", valoracion=" + valoracion +
                ", user=" + user.getNombre() +
                '}';
    }
}
