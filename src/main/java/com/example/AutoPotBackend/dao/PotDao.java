package com.example.AutoPotBackend.dao;

import com.example.AutoPotBackend.entity.*;
import org.springframework.stereotype.Repository;
import javax.jdo.annotations.Transactional;
import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Repository
public class PotDao {

    EntityManagerFactory emf;
    EntityManager em;

    public PotDao() {
        emf = Persistence.createEntityManagerFactory("objectdb:db/potted_plant.odb");
    }

    @Transactional
    public void addPot(Pot pot) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pot);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public void delPot(int pot_id) {
        em = emf.createEntityManager();
        Pot pot = em.find(Pot.class, pot_id);
        em.getTransaction().begin();
        em.remove(pot);
        em.getTransaction().commit();
        em.close();
    }

    public Long countPot() {
        em = emf.createEntityManager();
        TypedQuery<Long> query = em.createQuery("select count(p) from Pot p", Long.class);
        return query.getSingleResult();
    }

    public Pot getPot(int pot_id) {
        em = emf.createEntityManager();
        TypedQuery<Pot> query = em.createQuery("SELECT p FROM Pot p WHERE p.pot_id = " + pot_id, Pot.class);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return query.getResultList().get(0);
        }
    }

    public Pot getPot(String mfPotName) {
        em = emf.createEntityManager();
        TypedQuery<Pot> query = em.createQuery("SELECT p FROM Pot p WHERE p.pot_mf_name = '" + mfPotName + "'", Pot.class);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return query.getResultList().get(0);
        }
    }

    public Pot getPot(int user_id, String potName) {
        em = emf.createEntityManager();
        TypedQuery<Pot> query = em.createQuery("select p FROM Pot p WHERE p.user.user_id = " + user_id + " AND p.pot_name = '" + potName + "'", Pot.class);
        return query.getSingleResult();
    }

    public List<Pot> getPots(int user_id) {
        em = emf.createEntityManager();
        TypedQuery<Pot> query = em.createQuery("select p FROM Pot p WHERE p.user.user_id = " + user_id, Pot.class);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return query.getResultList();
        }
    }

    public List<Pot> getAllPots() {
        em = emf.createEntityManager();
        TypedQuery<Pot> query = em.createQuery("SELECT p FROM Pot p", Pot.class);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return query.getResultList();
        }
    }

    @Transactional
    public void updatePotName(int pot_id, String pot_name) {
        em = emf.createEntityManager();
        Pot updatePot = em.find(Pot.class, pot_id);
        em.getTransaction().begin();
        updatePot.setPot_name(pot_name);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public void updatePlantName(int pot_id, String plant_name) {
        em = emf.createEntityManager();
        Pot updatePot = em.find(Pot.class, pot_id);
        em.getTransaction().begin();
        updatePot.setPlant_name(plant_name);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public void updatePlantImg(int pot_id, String plant_img) {
        em = emf.createEntityManager();
        Pot updatePot = em.find(Pot.class, pot_id);
        em.getTransaction().begin();
        updatePot.setPlant_img(plant_img);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public void updatePlantType(int pot_id, String plant_type) {
        em = emf.createEntityManager();
        Pot updatePot = em.find(Pot.class, pot_id);
        em.getTransaction().begin();
        updatePot.setPlant_type(plant_type);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public void updateWatering(int pot_id, String wateringMode, String wateringStartTime, String wateringEndTime) {
        System.out.println("updateWateringMode...");
        em = emf.createEntityManager();
        Pot updatePot = em.find(Pot.class, pot_id);
        em.getTransaction().begin();
        updatePot.setWatering_mode(wateringMode);
        updatePot.setWatering_start_time(wateringStartTime);
        updatePot.setWatering_end_time(wateringEndTime);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public void updateLight(int pot_id, String lightMode, String lightStartTime, String lightEndTime) {
        em = emf.createEntityManager();
        Pot updatePot = em.find(Pot.class, pot_id);
        em.getTransaction().begin();
        updatePot.setLight_mode(lightMode);
        updatePot.setLight_start_time(lightStartTime);
        updatePot.setLight_end_time(lightEndTime);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public void updatePlantNameState(int pot_id, int plantNameState) {
        em = emf.createEntityManager();
        Pot updatePot = em.find(Pot.class, pot_id);
        em.getTransaction().begin();
        updatePot.setPlant_name_change_state(plantNameState);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public void updatePlantImgState(int pot_id, int plantImgState) {
        em = emf.createEntityManager();
        Pot updatePot = em.find(Pot.class, pot_id);
        em.getTransaction().begin();
        updatePot.setPlant_img_change_state(plantImgState);
        em.getTransaction().commit();
        em.close();
    }
}
