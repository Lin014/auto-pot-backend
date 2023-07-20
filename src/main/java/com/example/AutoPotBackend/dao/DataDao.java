package com.example.AutoPotBackend.dao;

import com.example.AutoPotBackend.entity.Data;
import org.springframework.stereotype.Repository;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DataDao {

    EntityManagerFactory emf;
    EntityManager em;

    public DataDao() {
        emf = Persistence.createEntityManagerFactory("objectdb:db/potted_plant.odb");
    }

    @Transactional
    public void addTable() {
        em = emf.createEntityManager();
        em.getMetamodel().entity(Data.class);
    }

    @Transactional
    public void addData(Data data) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public void delData(int data_id) {
        em = emf.createEntityManager();
        Data data = em.find(Data.class, data_id);
        em.getTransaction().begin();
        em.remove(data);
        em.getTransaction().commit();
        em.close();
    }

    public Data getLastData(int pot_id) {
        em = emf.createEntityManager();
        TypedQuery<Integer> query = em.createQuery("select max(d.data_id) from Data d where d.pot.pot_id = " + pot_id, Integer.class);
        System.out.println("count data qty: " + query.getSingleResult());
        if (query.getSingleResult() == null || query.getSingleResult() == 0) {
            return null;
        } else {
            TypedQuery<Data> dataTypedQuery = em.createQuery("select d from Data d where d.data_id = " + query.getSingleResult(), Data.class);
            return dataTypedQuery.getResultList().get(0);
        }
    }

    public List<Data> getAllDataByPotId(int pot_id) {
        em = emf.createEntityManager();
        TypedQuery<Data> query = em.createQuery("select d from Data d where d.pot.pot_id = " + pot_id, Data.class);
        if (query.getResultList() == null) {
            return null;
        } else {
            return query.getResultList();
        }
    }

    public Integer getDataNum(int pot_id) {
        em = emf.createEntityManager();
        TypedQuery<Integer> query = em.createQuery("select max(d.data_id) from Data d where d.pot.pot_id = " + pot_id, Integer.class);
        if (query.getSingleResult() == null || query.getSingleResult() == 0) {
            return 0;
        } else {
            return query.getSingleResult();
        }
    }
}
