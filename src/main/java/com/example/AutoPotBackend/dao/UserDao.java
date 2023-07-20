package com.example.AutoPotBackend.dao;

import com.example.AutoPotBackend.entity.User;
import org.springframework.stereotype.Component;

import javax.jdo.annotations.Transactional;
import javax.persistence.*;
import java.util.List;

@Component
public class UserDao {

    EntityManagerFactory emf;
    EntityManager em;

    public UserDao() {
        emf = Persistence.createEntityManagerFactory("objectdb:db/potted_plant.odb");
    }

    @Transactional
    public void addUser(User user) {
        User addUser = new User(user.getUser_account(), user.getUser_password());
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(addUser);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public void delUser(int user_id) {
        User user = em.find(User.class, user_id);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public void updateUserPassword(int user_id, String password) {
        User updateUser = em.find(User.class, user_id);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        updateUser.setUser_password(password);
        em.getTransaction().commit();
        em.close();
    }

    public User getUser(int user_id) {
        em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE user_id = " + user_id, User.class);
        return query.getSingleResult();
    }

    public User getUser(String account) {
        em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE user_account = " + account, User.class);
        return query.getSingleResult();
    }

    public List<User> getAllUsers() {
        em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }
}
