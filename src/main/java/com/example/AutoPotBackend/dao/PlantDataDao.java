package com.example.AutoPotBackend.dao;

import com.example.AutoPotBackend.entity.PlantData;
import com.example.AutoPotBackend.entity.Pot;
import org.springframework.stereotype.Repository;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Repository
public class PlantDataDao {

    EntityManagerFactory emf;
    EntityManager em;

    public PlantDataDao() {
        emf = Persistence.createEntityManagerFactory("objectdb:db/potted_plant.odb");
    }

    @Transactional
    public void addPlantData(PlantData plantData) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        PlantData plantData1 = new PlantData(plantData.getPlant_type(), plantData.getLight_start_time(), plantData.getLight_end_time());
        em.persist(plantData1);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public void delPlantData(int plant_data_id) {
        em = emf.createEntityManager();
        PlantData plantData = em.find(PlantData.class, plant_data_id);
        em.getTransaction().begin();
        em.remove(plantData);
        em.getTransaction().commit();
        em.close();
    }

    public PlantData getPlantData(String plant_type) {
        em = emf.createEntityManager();
        TypedQuery<PlantData> query = em.createQuery("select p from PlantData p where plant_type = '" + plant_type + "'", PlantData.class);
        return query.getSingleResult();
    }
}
