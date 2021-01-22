package com.docsconsole.tutorials.client;


import com.docsconsole.tutorials.entity.Player;
import com.docsconsole.tutorials.utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainClient {


    public static void main(String[] args) {

        System.out.println("The main method@MainClient");

        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {

            transaction = entityManager.getTransaction();
            transaction.begin();

            /*
            Player player = entityManager.find(Player.class, 1000006l);
            entityManager.remove(player);

            Query query = entityManager.createQuery("DELETE from Player p where p.id = :id");
            query.setParameter("id", 1000007l);
            query.executeUpdate();
            */


            //To delete all the records in JPA
            /*
            Query query1 = entityManager.createQuery("DELETE from Player p");
            query1.executeUpdate();
            */

            List idList = new ArrayList();
            idList.add(1000008);
            idList.add(1000009);
            String idParams = idList.stream().map(Object::toString).collect(Collectors.joining(",")).toString();
            //To delete all the records in JPA

            Query query1 = entityManager.createQuery("DELETE from Player p WHERE p.id IN ("+idParams+")");
            query1.executeUpdate();


            transaction.commit();


        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


}
