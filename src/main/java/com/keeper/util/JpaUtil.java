package com.keeper.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
	
	private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("dev");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

}
