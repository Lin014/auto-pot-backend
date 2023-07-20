package com.example.AutoPotBackend.service;

import com.example.AutoPotBackend.dao.UserDao;
import com.example.AutoPotBackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public String register(User user) {
        List<User> userList = userDao.getAllUsers();
        for (User u : userList) {
            if (user.getUser_account().equals(u.getUser_account())) {
                return "Failed";
            }
        }
        userDao.addUser(user);
        return "Successful";
    }

    public User login(User user) {
        User checkUser = checkAccount(user.getUser_account());
        if (checkUser == null) {
            return null;
        } else {
            boolean result = checkPassword(user.getUser_password(), checkUser.getUser_password());
            if (result) {
                return checkUser;
            } else {
                return null;
            }
        }
    }

    public String clearUser(User user) {
        if (userDao.getUser(user.getUser_id()) != null) {
            userDao.delUser(user.getUser_id());
            return "Successful";
        } else {
            return "Failed";
        }
    }

    public void changePassword(User user) {
        userDao.updateUserPassword(user.getUser_id(), user.getUser_password());
    }

    public User getUser(int user_id) {
        return userDao.getUser(user_id);
    }

    private User checkAccount(String account) {
        List<User> userList = userDao.getAllUsers();
        for (User u : userList) {
            if (u.getUser_account().equals(account)) {
                return u;
            }
        }
        return null;
    }

    private boolean checkPassword(String enterPwd, String pwd) {
        return enterPwd.equals(pwd);
    }


}
