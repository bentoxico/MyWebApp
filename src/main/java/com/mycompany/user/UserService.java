package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void saveUser(User user){
        repo.save(user);
    }
}
