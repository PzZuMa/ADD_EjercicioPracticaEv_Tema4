package org.example.services;

import org.example.modelos.Comentario;
import org.example.modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class Services {
    private static EntityManagerFactory emf;

    public Services(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Historia de usuario 1
     * Registrar un nuevo usuario en la base de datos
     * @param user
     */
    public void registrarUsuario(Usuario user){
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Historia de usuario 2
     * Mostrar todos los comentarios de un usuario en especifico
     * @param nombre
     * @return
     */
    public List<Comentario> comentariosUsuario(String nombre){
        List<Comentario> resultado = null;
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Comentario> query = em.createQuery("SELECT c FROM Comentario c WHERE c.user.nombre = :nombre", Comentario.class)
                    .setParameter("nombre", nombre);
            resultado = query.getResultList();
        } catch ( Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    /**
     * Historia de usuario 3
     * Añadir un comentario a la plataforma
     * @param id
     */
    public void anadirComentario(Long id, Comentario comentario) {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Usuario user = em.find(Usuario.class, id);
            user.addComentario(comentario);
            this.registrarComentario(comentario);
            em.persist(user);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Historia de usuario 4
     * Mostrar los usuario que han realizado comentarios con la valoración máxima
     */
    public List<String> usuariosValoracionMaxima() {
        List<String> resultado = null;
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<String> query = em.createQuery(
                    "SELECT u.correo FROM Usuario u JOIN u.comentarios c WHERE c.valoracion = 10",
                    String.class
            );
            resultado = query.getResultList();
            em.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }


    public void borrarUsuarios(){
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Usuario");
            query.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarComentarios(){
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Comentario");
            query.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> mostrarUsuarios(){
        List<Usuario> resultado = null;
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
            resultado = query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public void registrarComentario(Comentario comentario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(comentario);
        em.getTransaction().commit();
        em.close();
    }
}
