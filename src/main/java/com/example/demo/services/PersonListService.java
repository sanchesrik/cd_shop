package com.example.demo.services;

import com.example.demo.domain.Disc;
import com.example.demo.domain.PersonList;
import com.example.demo.domain.Staff;
import com.example.demo.domain.User;
import com.example.demo.repositories.DiscRepository;
import com.example.demo.repositories.ListRepository;
import com.example.demo.repositories.StaffRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.errrors.ObjectNotFound;
import com.example.demo.services.requests.ListCreateRequest;
import com.example.demo.services.requests.ListEditRequest;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonListService {
  private final ListRepository listRepository;
  private final DiscRepository discRepository;
  private final UserRepository userRepository;
  private final StaffRepository staffRepository;

  public PersonListService(ListRepository listRepository, DiscRepository discRepository,
                            UserRepository userRepository, StaffRepository staffRepository) {
    this.listRepository = listRepository;
    this.discRepository = discRepository;
    this.userRepository = userRepository;
    this.staffRepository = staffRepository;
  }

  public List<PersonList> getAll() {
    return listRepository.findAll();
  }

  public PersonList findOne(Integer id) throws ObjectNotFound {
    Optional<PersonList> optionalList = listRepository.findById(id);
    if (optionalList.isPresent())
      return optionalList.get();
    throw new ObjectNotFound("List not founded");
  }

  public PersonList createList(final ListCreateRequest request) throws ObjectNotFound {
    Optional<Disc> optionalDisc = discRepository.findById(request.getDisc_id());
    if (optionalDisc.isEmpty())
      throw new ObjectNotFound("Disc is not found");

    Optional<Staff> optionalStaff = staffRepository.findById(request.getStaff_id());
    if (optionalStaff.isEmpty())
      throw new ObjectNotFound("Staff is not found");

    Optional<User> optionalUser = userRepository.findById(request.getUser_id());
    if (optionalUser.isEmpty())
      throw new ObjectNotFound("User is not found");

    PersonList list = new PersonList();
    list.setAction(request.getAction());
    list.setDisc(optionalDisc.get());
    list.setUser(optionalUser.get());
    list.setStaff(optionalStaff.get());
    return listRepository.save(list);
  }

  public PersonList updateList(final ListEditRequest request) throws ObjectNotFound {

    Optional<PersonList> optionalList = listRepository.findById(request.getId());
    if (optionalList.isEmpty())
      throw new ObjectNotFound("List is not found");

    Optional<Disc> optionalDisc = discRepository.findById(request.getDisc_id());
    if (optionalDisc.isEmpty())
      throw new ObjectNotFound("Disc is not found");

    Optional<Staff> optionalStaff = staffRepository.findById(request.getStaff_id());
    if (optionalStaff.isEmpty())
      throw new ObjectNotFound("Staff is not found");

    Optional<User> optionalUser = userRepository.findById(request.getUser_id());
    if (optionalUser.isEmpty())
      throw new ObjectNotFound("User is not found");

    PersonList list = new PersonList();
    list.setId(request.getId());
    list.setAction(request.getAction());
    list.setDisc(optionalDisc.get());
    list.setUser(optionalUser.get());
    list.setStaff(optionalStaff.get());
    return listRepository.save(list);
  }

  public Boolean deleteList(int id) {
    listRepository.deleteById(id);
    return true;
  }
}
