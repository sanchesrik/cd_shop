package com.example.demo.services;

import com.example.demo.domain.Users;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.errrors.ObjectNotFound;
import com.example.demo.services.requests.UserCreateRequest;
import com.example.demo.services.requests.UserEditRequest;
import javassist.NotFoundException;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UserRepository repository;

    public UsersService(UserRepository repository) {
        this.repository = repository;
    }

    public List<Users> getAllUsers(){
        return repository.findAll();
    }

    public Users createUser(final UserCreateRequest request){
        Users user = new Users();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        return repository.save(user);
    }

    public Users updateUser(final UserEditRequest request)throws ObjectNotFound{
        Optional<Users> optionalUser = repository.findById(request.getId());
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setFirstname(request.getFirstname());
            user.setLastname(request.getLastname());
            return repository.save(user);
        } else
            throw new ObjectNotFound("User not found");
    }

    public Boolean deleteUser(Integer id) {
        repository.deleteById(id);
        return true;
    }
}
