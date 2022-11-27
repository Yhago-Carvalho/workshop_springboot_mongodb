package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new ObjectNotFoundException("Object not found");
        }
        return user;
    }

    public User insert(User obj) {
        return userRepository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User obj) {
        Optional<User> newObj = userRepository.findById(obj.getId());
        updateData(newObj, obj);
        User objUpdated = userRepository.save(obj);
        return objUpdated;
    }

    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

    private void updateData(Optional<User> newObj, User obj) {
        newObj.get().setName(obj.getName());
        newObj.get().setEmail(obj.getEmail());
    }
}
