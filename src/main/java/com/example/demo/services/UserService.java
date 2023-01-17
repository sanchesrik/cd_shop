package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.errrors.ObjectNotFound;
import com.example.demo.services.requests.UserCreateRequest;
import com.example.demo.services.requests.UserEditRequest;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public List<User> getAllUser() {
    return repository.findAll();
  }

  public User createUser(final UserCreateRequest request) {
    User user = new User();
    user.setFirstname(request.getFirstname());
    user.setLastname(request.getLastname());
    return repository.save(user);
  }

  public User updateUser(final UserEditRequest request) throws ObjectNotFound {
    Optional<User> optionalUser = repository.findById(request.getId());
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
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
