package com.example.AutoPotBackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id @GeneratedValue
    private int user_id;
    private String user_account;
    private String user_password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Pot> pots;

    public User() {};
    public User(int user_id) {
        this.user_id=user_id;
    };

    public User(String account, String password) {
        this.user_account = account;
        this.user_password = password;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setPots(List<Pot> pots) {
        this.pots = pots;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public String getUser_password() {
        return user_password;
    }

    public List<Pot> getPots() {
        return pots;
    }
}
