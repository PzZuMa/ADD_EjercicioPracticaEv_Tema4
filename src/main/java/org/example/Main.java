package org.example;

import org.example.modelos.Comentario;
import org.example.modelos.Usuario;
import org.example.services.ObjectDB_Utils;
import org.example.services.Services;

public class Main {
    public static void main(String[] args) {
        Services services = new Services( new ObjectDB_Utils().getEMF());

        Usuario user1 = new Usuario();
        user1.setNombre("Juan");
        user1.setCorreo("juan@gmail.com");

        Usuario user2 = new Usuario();
        user2.setNombre("Pedro");
        user2.setCorreo("pedro@gmail.com");

        //Historia de usuario 1
        services.registrarUsuario(user1);
        services.registrarUsuario(user2);

        System.out.println("Usuarios registrados");

        Comentario c1 = new Comentario();
        c1.setTexto("Normal esta pelicula");
        c1.setValoracion(5.0);

        Comentario c2 = new Comentario();
        c2.setTexto("Muy buena pelicula");
        c2.setValoracion(10.0);

        user1.addComentario(c1);
        services.registrarComentario(c1);

        user2.addComentario(c2);
        services.registrarComentario(c2);

        // Historia de usuario 2
        System.out.println("\nComentarios de Juan:");
        services.comentariosUsuario("Juan").forEach(System.out::println);

        Comentario c3 = new Comentario();
        c3.setTexto("Muy mala pelicula");
        c3.setValoracion(1.0);

        // Historia de usuario 3
        System.out.println("\nComentario añadido por Juan, ahora sus comentarios son:");
        services.anadirComentario(user1.getId(), c3);
        services.comentariosUsuario("Juan").forEach(System.out::println);

        // Historia de usuario 4
        System.out.println("\nCorreos de usuarios con comentarios de valoracion maxima:");
        services.usuariosValoracionMaxima().forEach(System.out::println);

        System.out.println("\nTodos los usuarios registrados:");
        services.mostrarUsuarios().forEach(System.out::println);
        services.borrarUsuarios();
        services.borrarComentarios();
    }
}