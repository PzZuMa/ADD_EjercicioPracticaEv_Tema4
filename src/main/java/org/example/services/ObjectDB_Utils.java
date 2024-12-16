package org.example.services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObjectDB_Utils {
    private static EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("examenBD.odb");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public EntityManagerFactory getEMF(){
        return emf;
    }
}
