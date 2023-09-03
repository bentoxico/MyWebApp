package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteUser(User user){
        repo.delete(user);
    }

    public User getUser(Integer id) throws UserNotFoundException {

        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find user with ID: " + id);

    }
}


